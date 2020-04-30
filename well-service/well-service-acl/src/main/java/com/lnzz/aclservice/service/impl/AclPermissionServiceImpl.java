package com.lnzz.aclservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.aclservice.pojo.AclPermission;
import com.lnzz.aclservice.mapper.AclPermissionMapper;
import com.lnzz.aclservice.pojo.AclRolePermission;
import com.lnzz.aclservice.pojo.AclUser;
import com.lnzz.aclservice.service.AclPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.aclservice.service.AclRolePermissionService;
import com.lnzz.aclservice.service.AclUserService;
import com.lnzz.aclservice.utils.MenuHelper;
import com.lnzz.aclservice.utils.PermissionHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@Service
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {
    @Autowired
    private AclRolePermissionService rolePermissionService;
    @Autowired
    private AclUserService userService;

    @Override
    public List<AclPermission> queryAllMenu() {
        QueryWrapper<AclPermission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<AclPermission> permissionList = baseMapper.selectList(wrapper);
        return PermissionHelper.build(permissionList);
    }


    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        this.selectChildListById(id, idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void saveRolePermissionRelationShip(String roleId, String[] permissionId) {

        rolePermissionService.remove(new QueryWrapper<AclRolePermission>().eq("role_id", roleId));

        List<AclRolePermission> rolePermissionList = new ArrayList<>();
        for (String perId : permissionId) {
            if (StringUtils.isEmpty(perId)) {
                continue;
            }
            AclRolePermission rolePermission = new AclRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(perId);
            rolePermissionList.add(rolePermission);
        }
        //添加到角色菜单关系表
        rolePermissionService.saveBatch(rolePermissionList);
    }

    @Override
    public List<AclPermission> selectAllMenu(String roleId) {
        List<AclPermission> allPermissionList = baseMapper.selectList(new QueryWrapper<AclPermission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<AclRolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<AclRolePermission>().eq("role_id", roleId));
        //转换给角色id与角色权限对应Map对象
        List<String> permissionIdList = rolePermissionList.stream().map(AclRolePermission::getPermissionId).collect(Collectors.toList());
        allPermissionList.forEach(permission -> {
            if (permissionIdList.contains(permission.getId())) {
                permission.setSelect(true);
            } else {
                permission.setSelect(false);
            }
        });

        return PermissionHelper.build(allPermissionList);
    }

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String id) {
        List<AclPermission> selectPermissionList = null;
        if(this.isSysAdmin(id)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(id);
        }

        List<AclPermission> permissionList = PermissionHelper.build(selectPermissionList);
        return MenuHelper.build(permissionList);
    }


    /**
     * 递归获取子节点
     *
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<AclPermission> childList = baseMapper.selectList(new QueryWrapper<AclPermission>().eq("pid", id).select("id"));
        childList.forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        AclUser user = userService.getById(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

}
