package com.lnzz.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.commonutils.JwtUtils;
import com.lnzz.orderservice.pojo.EduOrder;
import com.lnzz.orderservice.service.EduOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/orderservice/edu-order")
@Api(value = "订单管理", tags = {"订单管理相关接口"})
public class EduOrderController {
    @Autowired
    private EduOrderService eduOrderService;

    @ApiOperation(value = "创建订单", notes = "创建订单", httpMethod = "POST")
    @PostMapping("/createOrder")
    public JsonResult createOrder(@ApiParam(name = "courseId", value = "课程id", required = true)
                                  @RequestParam String courseId,
                                  HttpServletRequest request) {

        String orderId = eduOrderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return JsonResult.ok().data("orderId", orderId);
    }

    @ApiOperation(value = "根据订单id获取订单详情", notes = "根据订单id获取订单详情", httpMethod = "GET")
    @GetMapping("/getOrderInfoById")
    public JsonResult getOrderInfoById(@ApiParam(name = "orderId", value = "订单id", required = true)
                                       @RequestParam String orderId) {
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        EduOrder order = eduOrderService.getOne(wrapper);
        return JsonResult.ok().data("item", order);
    }

    @ApiOperation(value = "根据用户id和课程id查询订单信息", notes = "根据用户id和课程id查询订单信息", httpMethod = "GET")
    @GetMapping("/isBuyCourse")
    public boolean isBuyCourse(@ApiParam(name = "memberId", value = "会员id", required = true)
                               @RequestParam String memberId,
                               @ApiParam(name = "courseId", value = "课程id", required = true)
                               @RequestParam String courseId) {
        int count = eduOrderService.count(new QueryWrapper<EduOrder>().eq("member_id", memberId).eq("course_id", courseId).eq("status", 1));
        return count > 0;
    }
}

