package com.lnzz.aclservice.mapper;

import com.lnzz.aclservice.pojo.AclPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
public interface AclPermissionMapper extends BaseMapper<AclPermission> {

    /**
     * 查询所有权限
     *
     * @return
     */
    List<String> selectAllPermissionValue();

    /**
     * 根据用户id查询权限
     *
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * selectPermissionByUserId
     *
     * @param userId
     * @return
     */
    List<AclPermission> selectPermissionByUserId(String userId);
}
