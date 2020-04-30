package com.lnzz.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.lnzz.aclservice.service.IndexService;
import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName：IndexController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 16:54
 * @Description
 */
@RestController
@RequestMapping("/admin/acl/index")
@Api(value = "用户管理", tags = {"用户管理相关接口"})
public class IndexController {

    @Autowired
    private IndexService indexService;

    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息", httpMethod = "GET")
    @GetMapping("/info")
    public JsonResult info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return JsonResult.ok().data(userInfo);
    }

    @ApiOperation(value = "获取菜单", notes = "获取菜单", httpMethod = "GET")
    @GetMapping("/menu")
    public JsonResult getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return JsonResult.ok().data("permissionList", permissionList);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JsonResult logout(){
        return JsonResult.ok();
    }

}
