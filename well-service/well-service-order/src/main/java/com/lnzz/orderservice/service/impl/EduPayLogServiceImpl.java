package com.lnzz.orderservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.lnzz.commonutils.HttpClient;
import com.lnzz.orderservice.pojo.EduOrder;
import com.lnzz.orderservice.pojo.EduPayLog;
import com.lnzz.orderservice.mapper.EduPayLogMapper;
import com.lnzz.orderservice.service.EduOrderService;
import com.lnzz.orderservice.service.EduPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.orderservice.util.ConstantPropertiesUtils;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
@Service
public class EduPayLogServiceImpl extends ServiceImpl<EduPayLogMapper, EduPayLog> implements EduPayLogService {

    @Autowired
    private EduOrderService eduOrderService;

    @Override
    public Map createNative(String orderNo) {
        try {
            //根据订单id获取订单信息
            QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no", orderNo);
            EduOrder order = eduOrderService.getOne(wrapper);


            Map m = new HashMap<>(16);
            //1、设置支付参数
            m.put("appid", ConstantPropertiesUtils.APP_ID);
            m.put("mch_id", ConstantPropertiesUtils.PARTNER);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", ConstantPropertiesUtils.NOTIFY_URL + "\n");
            m.put("trade_type", "NATIVE");
            //3 发送httpclient请求，传递参数xml格式，微信支付提供的固定的地址
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //设置xml格式的参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, ConstantPropertiesUtils.PARTNER_KEY));
            client.setHttps(true);
            //执行post请求发送
            client.post();

            //4 得到发送请求返回结果
            //返回内容，是使用xml格式返回
            String xml = client.getContent();

            //把xml格式转换map集合，把map集合返回
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //最终返回数据 的封装
            Map map = new HashMap();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            //返回二维码操作状态码
            map.put("result_code", resultMap.get("result_code"));
            //二维码地址
            map.put("code_url", resultMap.get("code_url"));

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WellParamException(20001, "生成二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", ConstantPropertiesUtils.APP_ID);
            m.put("mch_id", ConstantPropertiesUtils.PARTNER);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, ConstantPropertiesUtils.PARTNER_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取订单id
        String orderNo = map.get("out_trade_no");
        //根据订单id查询订单信息
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        EduOrder order = eduOrderService.getOne(wrapper);

        if (order.getStatus().intValue() == 1) {
            return;
        }
        order.setStatus(1);
        eduOrderService.updateById(order);

        //记录支付日志
        EduPayLog payLog = new EduPayLog();
        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);
    }
}
