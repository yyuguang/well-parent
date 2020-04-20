package com.lnzz.cmsservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.cmsservice.pojo.CmsBanner;
import com.lnzz.cmsservice.service.CmsBannerService;
import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/educms/cms-banner-front")
@Api(value = "首页轮播图", tags = {"首页轮播图相关接口"})
public class CmsBannerFrontController {

    @Autowired
    private CmsBannerService cmsBannerService;

    @ApiOperation(value = "轮播图列表查询", notes = "轮播图列表查询", httpMethod = "GET")
    @GetMapping("/list")
    public JsonResult list() {
        QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        List<CmsBanner> list = cmsBannerService.list(wrapper);
        return JsonResult.ok().data("list", list);
    }
}

