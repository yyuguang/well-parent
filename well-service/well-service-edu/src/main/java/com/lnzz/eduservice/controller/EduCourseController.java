package com.lnzz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.pojo.vo.EduCourseInfoVo;
import com.lnzz.eduservice.pojo.vo.EduCoursePublishVo;
import com.lnzz.eduservice.pojo.vo.EduCourseQueryVo;
import com.lnzz.eduservice.service.EduCourseService;
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
@RequestMapping("/eduservice/edu-course")
@Api(value = "课程管理", tags = {"课程管理相关接口"})
public class EduCourseController extends BaseController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation(value = "添加课程基本信息", notes = "添加课程基本信息", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody EduCourseInfoVo vo) {

        String courseId = eduCourseService.saveCourseInfo(vo);
        return JsonResult.ok().data("courseId", courseId);
    }

    @ApiOperation(value = "通过课程id获取课程信息", notes = "通过课程id获取课程信息", httpMethod = "GET")
    @GetMapping("/getOneById")
    public JsonResult getOneById(@ApiParam(name = "courseId", value = "课程id", required = true)
                                 @RequestParam String courseId) {

        EduCourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return JsonResult.ok().data("courseInfoVo", courseInfoVo);
    }

    @ApiOperation(value = "编辑课程信息", notes = "编辑课程信息", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody EduCourseInfoVo eduCourseInfoVo) {
        eduCourseService.updateCourseInfo(eduCourseInfoVo);
        return JsonResult.ok();
    }

    @ApiOperation(value = "通过课程id获取课程发布信息", notes = "通过课程id获取课程发布信息", httpMethod = "GET")
    @GetMapping("/getPublishCourseInfo")
    public JsonResult getPublishCourseInfo(@ApiParam(name = "courseId", value = "课程id", required = true)
                                           @RequestParam String courseId) {
        EduCoursePublishVo data = eduCourseService.getPublishCourseInfo(courseId);
        return JsonResult.ok().data("data", data);
    }

    @ApiOperation(value = "课程最终发布", notes = "课程最终发布", httpMethod = "POST")
    @PostMapping("/publish")
    public JsonResult publish(@ApiParam(name = "courseId", value = "课程id", required = true)
                              @RequestParam String courseId) {
        boolean result = eduCourseService.publishCourseById(courseId);
        return result ? JsonResult.ok() : JsonResult.error();
    }

    @ApiOperation(value = "课程列表", notes = "课程列表", httpMethod = "POST")
    @PostMapping("/list/{pageNum}/{pageSize}")
    public JsonResult list(@ApiParam(name = "pageNum", value = "页数", required = false)
                           @PathVariable("pageNum") Integer pageNum,
                           @ApiParam(name = "pageSize", value = "每页显示条数", required = false)
                           @PathVariable("pageSize") Integer pageSize,
                           @RequestBody(required = false) EduCourseQueryVo queryVo) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        Page<EduCourse> coursePage = new Page<>(pageNum, pageSize);
        eduCourseService.pageByKeys(coursePage, queryVo);
        long total = coursePage.getTotal();
        List<EduCourse> rows = coursePage.getRecords();

        return JsonResult.ok().data("total", total).data("rows", rows);
    }

    @ApiOperation(value = "删除课程", notes = "删除课程", httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public JsonResult delete(@ApiParam(name = "courseId", value = "课程id", required = true)
                             @RequestParam String courseId) {
        boolean result = eduCourseService.removeCourse(courseId);
        return result ? JsonResult.ok() : JsonResult.error();
    }


}

