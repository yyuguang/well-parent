package com.lnzz.eduservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduChapter;
import com.lnzz.eduservice.pojo.vo.EduChapterVo;
import com.lnzz.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@Api(value = "课程章节管理", tags = {"课程章节管理相关接口"})
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    @ApiOperation(value = "课程章节小节列表", notes = "课程章节小节列表", httpMethod = "GET")
    @GetMapping("/list")
    public JsonResult list(@ApiParam(name = "courseId", value = "课程id", required = true)
                           @RequestParam String courseId) {
        List<EduChapterVo> list = chapterService.getChapterAndVideoListByCourseId(courseId);
        return JsonResult.ok().data("list", list);
    }

    @ApiOperation(value = "保存课程章节", notes = "保存课程章节", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return JsonResult.ok();
    }

    @ApiOperation(value = "编辑课程章节", notes = "编辑课程章节", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return JsonResult.ok();
    }

    @ApiOperation(value = "通过章节id获取章节信息", notes = "通过章节id获取章节信息", httpMethod = "GET")
    @GetMapping("/getOneById")
    public JsonResult getOneById(@ApiParam(name = "chapterId", value = "章节id", required = true)
                                 @RequestParam String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return JsonResult.ok().data("eduChapter", eduChapter);
    }

    @ApiOperation(value = "删除章节信息", notes = "删除章节信息", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JsonResult delete(@ApiParam(name = "chapterId", value = "章节id", required = true)
                             @RequestParam String chapterId) {
        boolean flag = chapterService.deleteChapterById(chapterId);
        return flag ? JsonResult.ok() : JsonResult.error();
    }


}

