package com.lnzz.aclservice.service;

import com.lnzz.aclservice.pojo.AclRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
public interface AclRoleService extends IService<AclRole> {

    /**
     * 根据用户获取角色数据
     *
     * @param userId
     * @return
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * 根据用户分配角色
     *
     * @param userId
     * @param roleIds
     */
    void saveUserRoleRelationShip(String userId, String[] roleIds);

    /**
     * selectRoleByUserId
     *
     * @param id
     * @return
     */
    List<AclRole> selectRoleByUserId(String id);
}
