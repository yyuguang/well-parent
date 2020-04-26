package com.lnzz.orderservice.client;

import com.lnzz.commonutils.ordervo.UcenterMemberOrderVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName：UcenterClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/25 18:03
 * @Description
 */
@Component
@FeignClient("well-service-ucenter")
public interface UcenterClient {
    /**
     * 订单根据会员id获取会员信息
     *
     * @param id
     * @return
     */
    @PostMapping("/eduucenter/edu-ucenter-front/getMemberInfoOrder")
    UcenterMemberOrderVo getMemberInfoOrder(@ApiParam(name = "id", value = "会员id", required = true)
                                            @RequestParam("id") String id);
}
