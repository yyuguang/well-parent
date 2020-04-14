package com.lnzz.wellserviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName：OssService
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/14 22:07
 * @Description
 */
public interface OssService {


    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
