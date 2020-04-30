package com.lnzz.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.lnzz.aclservice.pojo.AclPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
public interface AclPermissionService extends IService<AclPermission> {

    /**
     * 获取全部菜单
     *
     * @return
     */
    List<AclPermission> queryAllMenu();

    /**
     * 删除菜单
     *
     * @param id
     */
    void removeChildById(String id);

    /**
     * 给角色分配权限
     *
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRelationShip(String roleId, String[] permissionId);

    /**
     * 根据角色获取菜单
     *
     * @param roleId
     * @return
     */
    List<AclPermission> selectAllMenu(String roleId);

    /**
     * 查询权限值
     *
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * 查询权限
     *
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId(String id);
}
