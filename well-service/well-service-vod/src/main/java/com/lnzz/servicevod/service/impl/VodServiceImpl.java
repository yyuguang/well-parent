package com.lnzz.servicevod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import com.lnzz.servicevod.service.VodService;
import com.lnzz.servicevod.utils.ConstantPropertiesUtils;
import com.lnzz.servicevod.utils.InitVodClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassName：VodServiceImpl
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/17 15:23
 * @Description
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {
            //上传文件原始名称
            String fileName = file.getOriginalFilename();
            //上传之后显示的名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            /* 模板组ID(可选) */
            request.setTemplateGroupId(ConstantPropertiesUtils.ACCESS_TEMPLATE_GROUPID);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            log.info("RequestId{}", response.getRequestId());
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
                log.error("VideoId{}", response.getVideoId());
                log.error("ErrorCode{}", response.getCode());
                log.error("ErrorMessage{}", response.getMessage());
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void removeAliVideo(String id) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WellParamException(20001, "删除阿里云视频失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void removeVideoList(List<String> videoIdList) {
        try {
            //初始化
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            //创建请求对象
            //一次只能批量删20个
            String str = StringUtils.join(videoIdList.toArray(), ",");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(str);

            //获取响应
            DeleteVideoResponse response = client.getAcsResponse(request);
            log.info("RequestId{}", response.getRequestId());

        } catch (Exception e) {
            throw new WellParamException(20001, "删除阿里云视频失败");
        }
    }
}
