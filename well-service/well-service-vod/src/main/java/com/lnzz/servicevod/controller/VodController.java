package com.lnzz.servicevod.controller;

import com.lnzz.commonutils.JsonResult;
import com.lnzz.servicevod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName：VodController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/17 15:21
 * @Description
 */
@RestController
@RequestMapping("/eduvod/video")
@Api(value = "阿里云视频", tags = {"阿里云视频相关接口"})
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation(value = "上传视频", notes = "上传视频", httpMethod = "POST")
    @PostMapping("/uploadAliVideo")
    public JsonResult uploadAliVideo(MultipartFile file) {
        String videoId = vodService.uploadAliVideo(file);
        return JsonResult.ok().data("videoId", videoId);
    }

    @ApiOperation(value = "删除视频", notes = "删除视频", httpMethod = "DELETE")
    @DeleteMapping("/removeAliVideo")
    public JsonResult removeAliVideo(@ApiParam(name = "id", value = "视频id", required = true)
                                     @RequestParam String id) {
        vodService.removeAliVideo(id);
        return JsonResult.ok();
    }

    @ApiOperation(value = "批量删除视频", notes = "批量删除视频", httpMethod = "DELETE")
    @DeleteMapping("/deleteBatch")
    public JsonResult removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){

        vodService.removeVideoList(videoIdList);
        return JsonResult.ok().message("视频删除成功");
    }


}
