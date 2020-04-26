package com.lnzz.eduservice.client;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName：OrderClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/26 20:25
 * @Description
 */
@Component
@FeignClient(value = "well-service-order")
public interface OrderClient {

    /**
     * 根据用户id和课程id查询订单信息
     *
     * @param memberId
     * @param courseId
     * @return
     */
    @ApiOperation(value = "根据用户id和课程id查询订单信息", notes = "根据用户id和课程id查询订单信息", httpMethod = "GET")
    @GetMapping("/orderservice/edu-order/isBuyCourse")
    boolean isBuyCourse(@ApiParam(name = "memberId", value = "会员id", required = true)
                        @RequestParam String memberId,
                        @ApiParam(name = "courseId", value = "课程id", required = true)
                        @RequestParam String courseId);
}
