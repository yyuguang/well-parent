package com.lnzz.staservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
@Api(value = "统计分析管理", tags = {"统计分析管理相关接口"})
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation(value = "统计某一天的注册人数", notes = "统计某一天的注册人数", httpMethod = "POST")
    @PostMapping("/registerCount")
    public JsonResult registerCount(@ApiParam(name = "day", value = "天数", required = true)
                                    @RequestParam String day) {

        statisticsDailyService.registerCount(day);
        return JsonResult.ok();
    }

    @ApiOperation(value = "图表显示", notes = "图表显示", httpMethod = "GET")
    @GetMapping("/showData")
    public JsonResult showData(@RequestParam String type,
                               @RequestParam String begin,
                               @RequestParam String end) {
        Map<String, Object> map = statisticsDailyService.getChatData(type, begin, end);
        return JsonResult.ok().data(map);
    }

}

