package com.lnzz.msmservice.controller;

import com.lnzz.commonutils.JsonResult;
import com.lnzz.commonutils.RandomUtil;
import com.lnzz.msmservice.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ClassName：MsmController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 15:07
 * @Description
 */
@RestController
@RequestMapping("/edumsm/msm")
@Api(value = "阿里云短信管理", tags = {"阿里云短信管理相关接口"})
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "发送短信", notes = "发送短信", httpMethod = "POST")
    @PostMapping("/send")
    public JsonResult sendMsm(@ApiParam(name = "phone", value = "手机号", required = true)
                              @RequestParam String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return JsonResult.ok();
        }

        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>(16);
        param.put("code", code);

        boolean result = msmService.send(param, phone);
        if (result) {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return JsonResult.ok();
        } else {
            return JsonResult.error().message("短信发送失败");
        }
    }
}
