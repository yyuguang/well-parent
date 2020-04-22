package com.lnzz.ucenterservice.controller.front;

import com.lnzz.commonutils.JsonResult;
import com.lnzz.commonutils.JwtUtils;
import com.lnzz.ucenterservice.pojo.vo.LoginInfoVo;
import com.lnzz.ucenterservice.pojo.vo.LoginVo;
import com.lnzz.ucenterservice.pojo.vo.RegisterVo;
import com.lnzz.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName：UcenterMemberController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 16:48
 * @Description
 */
@RestController
@RequestMapping("/eduucenter/edu-ucenter-front")
@Api(value = "前台注册登录管理", tags = {"前台注册登录管理相关接口"})
public class UcenterMemberFrontController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return JsonResult.ok().data("token", token);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public JsonResult register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return JsonResult.ok();
    }

    @ApiOperation(value = "根据token获取登录信息", notes = "根据token获取登录信息", httpMethod = "GET")
    @GetMapping("/getLoginInfo")
    public JsonResult getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
        return JsonResult.ok().data("item", loginInfoVo);
    }
}
