package com.lnzz.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.cmsservice.pojo.CmsBanner;
import com.lnzz.cmsservice.mapper.CmsBannerMapper;
import com.lnzz.cmsservice.service.CmsBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-19
 */
@Service
public class CmsBannerServiceImpl extends ServiceImpl<CmsBannerMapper, CmsBanner> implements CmsBannerService {

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CmsBanner> getAll() {
        QueryWrapper<CmsBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        return baseMapper.selectList(null);
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean saveBanner(CmsBanner cmsBanner) {
        int result = baseMapper.insert(cmsBanner);
        return result > 0;
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean updateBanner(CmsBanner cmsBanner) {
        int result = baseMapper.updateById(cmsBanner);
        return result > 0;
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean removeBannerById(String id) {
        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}
