package com.lnzz.wellserviceoss.controller;

import com.lnzz.commonutils.JsonResult;
import com.lnzz.wellserviceoss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName：OssController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/14 22:07
 * @Description
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@Api(value = "阿里云OSS", tags = {"阿里云OSS相关接口"})
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/uploadOssFile")
    @ApiOperation(value = "上传头像", notes = "上传头像", httpMethod = "POST")
    public JsonResult uploadOssFile(MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return JsonResult.ok().data("url",url);
    }
}
