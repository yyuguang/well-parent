package com.lnzz.eduservice.controller;

import com.lnzz.commonutils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName：EduLoginController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/14 8:37
 * @Description
 */
@RestController
@RequestMapping("/eduservice/user")
@Api(value = "用户登录", tags = {"用户登录相关接口"})
public class EduLoginController {

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JsonResult login(){
        //TODO 模拟登录，后期完善
        return JsonResult.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息", notes = "用户信息", httpMethod = "GET")
    @GetMapping("/info")
    public JsonResult info() {
        //TODO 模拟获取，后期完善
        return JsonResult.ok().data("roles","[admin]").data("name","admin").data("avatar","http://182.61.1.209/foodie/user_face.jpg");
    }


}
