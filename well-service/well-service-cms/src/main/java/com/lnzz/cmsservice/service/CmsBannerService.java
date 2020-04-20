package com.lnzz.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.cmsservice.pojo.CmsBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-19
 */
public interface CmsBannerService extends IService<CmsBanner> {

    /**
     * 获取banner列表
     *
     * @return
     */
    List<CmsBanner> getAll();

    /**
     * 新增banner
     *
     * @param cmsBanner
     * @return
     */
    boolean saveBanner(CmsBanner cmsBanner);

    /**
     * 编辑banner
     *
     * @param cmsBanner
     * @return
     */
    boolean updateBanner(CmsBanner cmsBanner);

    /**
     * 通过id删除banner
     *
     * @param id
     * @return
     */
    boolean removeBannerById(String id);

}
