package com.lnzz.eduservice.client;

import com.lnzz.commonutils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName：EduVodClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/18 14:51
 * @Description
 */
@FeignClient(name = "well-service-vod", fallback = EduVodFileDegradeFeignClient.class)
@Component
public interface EduVodClient {

    /**
     * 删除阿里云视频
     *
     * @param id
     * @return
     */
    @DeleteMapping("/eduvod/video/removeAliVideo")
    JsonResult removeAliVideo(@RequestParam("id") String id);

    /**
     * 批量删除阿里云视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteBatch")
    JsonResult removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
