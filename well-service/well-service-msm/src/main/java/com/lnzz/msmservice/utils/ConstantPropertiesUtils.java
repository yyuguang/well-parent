package com.lnzz.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName：ConstantPropertiesUtils
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 15:35
 * @Description
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    @Value("${aliyun.msm.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.msm.secret}")
    private String secret;

    public static String ACCESS_KEY_ID;

    public static String SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        SECRET = secret;
    }
}
