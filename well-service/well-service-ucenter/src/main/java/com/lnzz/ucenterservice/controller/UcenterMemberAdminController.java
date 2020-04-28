package com.lnzz.ucenterservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.ucenterservice.pojo.UcenterMember;
import com.lnzz.ucenterservice.pojo.vo.QueryVo;
import com.lnzz.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName：UcenterMemberAdminController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/22 9:18
 * @Description
 */
@RestController
@RequestMapping("/eduucenter/edu-ucenter-admin")
@Api(value = "后台会员管理", tags = {"后台会员管理相关接口"})
public class UcenterMemberAdminController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员列表", notes = "会员列表", httpMethod = "POST")
    @PostMapping("/list/{pageNum}/{pageSize}")
    public JsonResult list(@ApiParam(name = "pageNum", value = "页数", required = false)
                           @PathVariable("pageNum") Integer pageNum,
                           @ApiParam(name = "pageSize", value = "每页显示条数", required = false)
                           @PathVariable("pageSize") Integer pageSize,
                           @RequestBody(required = false) QueryVo queryVo) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }
        Page<UcenterMember> memberPage = new Page<>(pageNum, pageSize);
        ucenterMemberService.pageKeys(memberPage, queryVo);
        long total = memberPage.getTotal();
        List<UcenterMember> rows = memberPage.getRecords();
        return JsonResult.ok().data("total", total).data("rows", rows);
    }

    @ApiOperation(value = "禁用会员", notes = "禁用会员", httpMethod = "POST")
    @PostMapping("/disableMemberById")
    public JsonResult disableMemberById(@ApiParam(name = "id", value = "用户id", required = true)
                                        @RequestParam("id") String id) {
        ucenterMemberService.disableMemberById(id);
        return JsonResult.ok();
    }

    @ApiOperation(value = "获取单个会员信息", notes = "获取单个会员信息", httpMethod = "GET")
    @GetMapping("/getMemberInfo")
    public JsonResult getMemberInfo(@ApiParam(name = "id", value = "用户id", required = true)
                                    @RequestParam("id") String id) {
        UcenterMember member = ucenterMemberService.getById(id);
        return JsonResult.ok().data("data", member);
    }

    @ApiOperation(value = "查询某一天网站注册人数", notes = "查询某一天网站注册人数", httpMethod = "POST")
    @PostMapping("/countRegisterDay")
    public JsonResult countRegisterDay(@ApiParam(name = "day", value = "日期", required = true)
                                       @RequestParam("day") String day) {
        Integer count = ucenterMemberService.countRegisterDay(day);
        return JsonResult.ok().data("count", count);
    }
}
