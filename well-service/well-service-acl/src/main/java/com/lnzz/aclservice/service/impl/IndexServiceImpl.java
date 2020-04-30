package com.lnzz.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lnzz.aclservice.pojo.AclRole;
import com.lnzz.aclservice.pojo.AclUser;
import com.lnzz.aclservice.service.AclPermissionService;
import com.lnzz.aclservice.service.AclRoleService;
import com.lnzz.aclservice.service.AclUserService;
import com.lnzz.aclservice.service.IndexService;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName：IndexServiceImpl
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 15:56
 * @Description
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private AclUserService userService;

    @Autowired
    private AclRoleService roleService;

    @Autowired
    private AclPermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>();
        AclUser user = userService.selectByUsername(username);
        if (null == user) {
            throw new WellParamException(20001, "暂无该用户");
        }

        //根据用户id获取角色
        List<AclRole> roleList = roleService.selectRoleByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(AclRole::getRoleName).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        AclUser user = userService.selectByUsername(username);
        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}
