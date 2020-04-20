package com.lnzz.eduservice.controller.front;

import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.pojo.EduTeacher;
import com.lnzz.eduservice.service.EduCourseService;
import com.lnzz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName：IndexFrontController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/19 16:05
 * @Description
 */
@RestController
@RequestMapping("/eduservice/index-front")
@Api(value = "前端接口管理", tags = {"前端接口管理相关接口"})
public class IndexFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "首页查询前8门热门课程，4名名师", notes = "首页查询前8门热门课程，4名名师", httpMethod = "GET")
    @GetMapping("/index")
    public JsonResult index() {
        //查询热门课程
        List<EduCourse> courseList = eduCourseService.selectHotCourse();
        //查询热门讲师
        List<EduTeacher> teacherList = eduTeacherService.selectHotTeacher();
        return JsonResult.ok().data("course", courseList).data("teacher", teacherList);
    }
}
