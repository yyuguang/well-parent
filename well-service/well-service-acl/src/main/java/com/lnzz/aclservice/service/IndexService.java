package com.lnzz.aclservice.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * ClassName：IndexService
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 15:55
 * @Description
 */
public interface IndexService {
    /**
     * 根据用户名获取用户登录信息
     *
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     *
     * @param username
     * @return
     */
    List<JSONObject> getMenu(String username);
}
