package com.lnzz.cmsservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.cmsservice.pojo.CmsBanner;
import com.lnzz.cmsservice.service.CmsBannerService;
import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * ClassName：CmsBannerAdminController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/19 15:41
 * @Description
 */
@RestController
@RequestMapping("/educms/cms-banner-admin")
@Api(value = "首页轮播图后台管理", tags = {"首页轮播图后台管理相关接口"})
@CrossOrigin
public class CmsBannerAdminController extends BaseController {
    @Autowired
    private CmsBannerService cmsBannerService;

    @ApiOperation(value = "轮播图列表查询带分页", notes = "轮播图列表查询带分页", httpMethod = "GET")
    @GetMapping("/list")
    public JsonResult list(@ApiParam(name = "pageNum", value = "页数", required = false)
                           @RequestParam("pageNum") Integer pageNum,
                           @ApiParam(name = "pageSize", value = "每页显示条数", required = false)
                           @RequestParam("pageSize") Integer pageSize) {

        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        Page<CmsBanner> pageBanner = new Page<>(pageNum,pageSize);
        cmsBannerService.page(pageBanner,null);
        return JsonResult.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @ApiOperation(value = "根据id获取单个轮播图信息", notes = "根据id获取单个轮播图信息", httpMethod = "GET")
    @GetMapping("/getOne")
    public JsonResult getOne(@ApiParam(name = "id", value = "轮播图id", required = true)
                             @RequestParam("id") String id) {
        CmsBanner banner = cmsBannerService.getById(id);
        return JsonResult.ok().data("data", banner);
    }

    @ApiOperation(value = "保存轮播图", notes = "保存轮播图", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody CmsBanner cmsBanner) {
        boolean result = cmsBannerService.saveBanner(cmsBanner);
        return result ? JsonResult.ok() : JsonResult.error();
    }

    @ApiOperation(value = "编辑轮播图", notes = "编辑轮播图", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody CmsBanner cmsBanner) {
        boolean result = cmsBannerService.updateBanner(cmsBanner);
        return result ? JsonResult.ok() : JsonResult.error();
    }

    @ApiOperation(value = "删除轮播图", notes = "删除轮播图", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JsonResult delete(@ApiParam(name = "id", value = "轮播图id", required = true)
                             @RequestParam("id") String id) {
        boolean result = cmsBannerService.removeBannerById(id);
        return result ? JsonResult.ok() : JsonResult.error();
    }

}
