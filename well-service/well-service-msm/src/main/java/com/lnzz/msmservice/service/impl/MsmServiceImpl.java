package com.lnzz.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.lnzz.msmservice.service.MsmService;
import com.lnzz.msmservice.utils.ConstantPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName：MsmServiceImpl
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 15:08
 * @Description
 */
@Service
@Slf4j
public class MsmServiceImpl implements MsmService {


    @Override
    public boolean send(Map<String, Object> param, String phone) {

        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        //获取工具类值
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String secret = ConstantPropertiesUtils.SECRET;

        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        //设置参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "欢乐购");
        request.putQueryParameter("TemplateCode", "SMS_188630186");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("response{}", response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
