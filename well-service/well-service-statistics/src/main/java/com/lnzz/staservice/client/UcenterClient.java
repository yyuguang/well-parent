package com.lnzz.staservice.client;

import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName：UcenterClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/28 9:14
 * @Description
 */
@Component
@FeignClient("well-service-ucenter")
public interface UcenterClient {
    /**
     * countRegisterDay
     *
     * @param day
     * @return
     */
    @PostMapping("/eduucenter/edu-ucenter-admin/countRegisterDay")
    JsonResult countRegisterDay(@ApiParam(name = "day", value = "日期", required = true)
                                @RequestParam("day") String day);
}
