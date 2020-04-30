package com.lnzz.aclservice.service;

import com.lnzz.aclservice.pojo.AclUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
public interface AclUserService extends IService<AclUser> {

    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    AclUser selectByUsername(String username);
}
