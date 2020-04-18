package com.lnzz.servicevod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName：ConstantPropertiesUtils
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/14 22:03
 * @Description 阿里云oss配置常量类
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {


    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.templategroupId}")
    private String templateGroupId;


    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String ACCESS_TEMPLATE_GROUPID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        ACCESS_TEMPLATE_GROUPID = templateGroupId;
    }
}
