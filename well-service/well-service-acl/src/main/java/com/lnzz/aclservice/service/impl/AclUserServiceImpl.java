package com.lnzz.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.aclservice.pojo.AclUser;
import com.lnzz.aclservice.mapper.AclUserMapper;
import com.lnzz.aclservice.service.AclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-29
 */
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {

    @Override
    public AclUser selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<AclUser>().eq("username", username));
    }
}
