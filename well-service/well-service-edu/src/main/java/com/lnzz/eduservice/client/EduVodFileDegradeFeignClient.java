package com.lnzz.eduservice.client;

import com.lnzz.commonutils.JsonResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName：EduVodFileDegradeFeignClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/18 17:22
 * @Description
 */
@Component
public class EduVodFileDegradeFeignClient implements EduVodClient {

    @Override
    public JsonResult removeAliVideo(String id) {
        return JsonResult.error().message("删除视频出错");
    }

    @Override
    public JsonResult removeVideoList(List<String> videoIdList) {
        return JsonResult.error().message("删除多个视频出错");
    }

}
