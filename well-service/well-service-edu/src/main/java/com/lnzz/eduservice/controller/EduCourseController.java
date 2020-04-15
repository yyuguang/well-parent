package com.lnzz.eduservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.vo.EduCourseInfoVo;
import com.lnzz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation(value = "添加课程基本信息", notes = "添加课程基本信息", httpMethod = "POST")
    @PostMapping("/saveCourseInfo")
    public JsonResult saveCourseInfo(@RequestBody EduCourseInfoVo vo) {

        String courseId = eduCourseService.saveCourseInfo(vo);
        return JsonResult.ok().data("courseId", courseId);
    }

}

