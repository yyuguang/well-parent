package com.lnzz.orderservice.service.impl;

import com.lnzz.commonutils.OrderNoUtil;
import com.lnzz.commonutils.ordervo.EduCourseOrderVo;
import com.lnzz.commonutils.ordervo.UcenterMemberOrderVo;
import com.lnzz.orderservice.client.EduClient;
import com.lnzz.orderservice.client.UcenterClient;
import com.lnzz.orderservice.pojo.EduOrder;
import com.lnzz.orderservice.mapper.EduOrderMapper;
import com.lnzz.orderservice.service.EduOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
@Service
public class EduOrderServiceImpl extends ServiceImpl<EduOrderMapper, EduOrder> implements EduOrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String memberIdByJwtToken) {

        //远程调用根据用户id获取用户信息
        UcenterMemberOrderVo memberInfoOrder = ucenterClient.getMemberInfoOrder(memberIdByJwtToken);
        //远程调用根据课程id获取课程信息
        EduCourseOrderVo courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        EduOrder order = new EduOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(memberInfoOrder.getMobile());
        order.setNickname(memberInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
