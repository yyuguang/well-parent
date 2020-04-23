package com.lnzz.eduservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduVideo;
import com.lnzz.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@Api(value = "课程小节管理", tags = {"课程小节管理相关接口"})
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "保存课程小节信息", notes = "保存课程小节信息", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody EduVideo video) {
        eduVideoService.save(video);
        return JsonResult.ok();
    }

    @ApiOperation(value = "编辑课程小节信息", notes = "编辑课程小节信息", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody EduVideo video) {
        eduVideoService.updateById(video);
        return JsonResult.ok();
    }

    @ApiOperation(value = "通过小节id获取章节信息", notes = "通过小节id获取章节信息", httpMethod = "GET")
    @GetMapping("/getOneById")
    public JsonResult getOneById(@ApiParam(name = "videoId", value = "小节id", required = true)
                                 @RequestParam String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return JsonResult.ok().data("eduVideo", eduVideo);
    }

    /**
     *
     * @param videoId
     * @return
     */
    @ApiOperation(value = "删除课程小节信息", notes = "删除课程小节信息", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JsonResult delete(@ApiParam(name = "videoId", value = "小节id", required = true)
                             @RequestParam String videoId) {
        eduVideoService.removeAll(videoId);
        return JsonResult.ok();
    }

}

