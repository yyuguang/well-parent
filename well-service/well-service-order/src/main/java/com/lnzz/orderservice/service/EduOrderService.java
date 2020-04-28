package com.lnzz.orderservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.orderservice.pojo.EduOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.orderservice.pojo.vo.OrderQueryVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
public interface EduOrderService extends IService<EduOrder> {

    /**
     * 创建订单
     *
     * @param courseId
     * @param memberIdByJwtToken
     * @return
     */
    String saveOrder(String courseId, String memberIdByJwtToken);

    /**
     * 分页查询
     *
     * @param memberPage
     * @param queryVo
     */
    void pageKeys(Page<EduOrder> memberPage, OrderQueryVo queryVo);
}
