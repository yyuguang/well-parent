package com.lnzz.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName：VodService
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/17 15:22
 * @Description
 */
public interface VodService {

    /**
     * 阿里云上传视频
     *
     * @param file
     * @return
     */
    String uploadAliVideo(MultipartFile file);

    /**
     * 删除阿里云视频
     *
     * @param id
     * @return
     */
    void removeAliVideo(String id);
}
