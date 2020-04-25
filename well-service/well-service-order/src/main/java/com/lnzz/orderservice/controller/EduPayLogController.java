package com.lnzz.orderservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.orderservice.service.EduPayLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/orderservice/edu-pay-log")
public class EduPayLogController {

    @Autowired
    private EduPayLogService eduPayLogService;

    @ApiOperation(value = "生成微信支付二维码", notes = "生成微信支付二维码", httpMethod = "POST")
    @PostMapping("/createNative")
    public JsonResult createNative(@ApiParam(name = "orderNo", value = "orderNo", required = true)
                                   @RequestParam String orderNo) {
        Map map = eduPayLogService.createNative(orderNo);
        return JsonResult.ok().data(map);
    }

    @ApiOperation(value = "查询订单支付状态", notes = "查询订单支付状态", httpMethod = "GET")
    @GetMapping("/queryPayStatus")
    public JsonResult queryPayStatus(@ApiParam(name = "orderNo", value = "orderNo", required = true)
                                     @RequestParam String orderNo) {

        Map<String, String> map = eduPayLogService.queryPayStatus(orderNo);
        if (map == null) {
            return JsonResult.error().message("支付出错");
        }
        if ("SUCCESS".equals(map.get("trade_state"))) {
            //更改订单状态
            eduPayLogService.updateOrderStatus(map);
            return JsonResult.ok().message("支付成功");
        }

        return JsonResult.ok().code(25000).message("支付中");
    }
}

