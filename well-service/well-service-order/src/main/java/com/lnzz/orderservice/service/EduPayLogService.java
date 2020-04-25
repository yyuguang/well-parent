package com.lnzz.orderservice.service;

import com.lnzz.orderservice.pojo.EduPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
public interface EduPayLogService extends IService<EduPayLog> {

    /**
     * 生成微信支付二维码
     *
     * @param orderNo
     * @return
     */
    Map createNative(String orderNo);

    /**
     * 查询订单支付状态
     *
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 更新订单状态
     *
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);
}
