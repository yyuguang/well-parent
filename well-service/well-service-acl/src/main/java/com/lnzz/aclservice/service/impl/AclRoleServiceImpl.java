package com.lnzz.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.aclservice.pojo.AclRole;
import com.lnzz.aclservice.mapper.AclRoleMapper;
import com.lnzz.aclservice.pojo.AclUserRole;
import com.lnzz.aclservice.service.AclRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.aclservice.service.AclUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@Service
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {

    @Autowired
    private AclUserRoleService userRoleService;

    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<AclRole> allRolesList = baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<AclUserRole> existUserRoleList = userRoleService.list(new QueryWrapper<AclUserRole>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleList.stream().map(AclUserRole::getRoleId).collect(Collectors.toList());

        //对角色进行分类
        List<AclRole> assignRoles = new ArrayList<AclRole>();
        for (AclRole role : allRolesList) {
            //已分配
            if (existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    @Override
    public void saveUserRoleRelationShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<AclUserRole>().eq("user_id", userId));

        List<AclUserRole> userRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            AclUserRole userRole = new AclUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public List<AclRole> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<AclUserRole> userRoleList = userRoleService.list(new QueryWrapper<AclUserRole>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(AclUserRole::getRoleId).collect(Collectors.toList());
        List<AclRole> roleList = new ArrayList<>();
        if (roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
