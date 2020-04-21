package com.lnzz.msmservice.service;

import java.util.Map;

/**
 * ClassName：MsmService
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 15:08
 * @Description
 */
public interface MsmService {
    /**
     * 发送短信
     *
     * @param param
     * @param phone
     * @return
     */
    boolean send(Map<String, Object> param, String phone);
}
