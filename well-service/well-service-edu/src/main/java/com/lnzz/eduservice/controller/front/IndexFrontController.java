package com.lnzz.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.pojo.EduTeacher;
import com.lnzz.eduservice.service.EduCourseService;
import com.lnzz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "讲师分页查询", notes = "讲师分页查询", httpMethod = "GET")
    @GetMapping("/teacherPageList")
    public JsonResult teacherPageList(@ApiParam(name = "pageNum", value = "页数", required = true)
                                      @RequestParam("pageNum") Integer pageNum,
                                      @ApiParam(name = "pageSize", value = "每页显示条数", required = true)
                                      @RequestParam("pageSize") Integer pageSize) {

        Page<EduTeacher> pageParam = new Page<EduTeacher>(pageNum, pageSize);

        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);

        return JsonResult.ok().data(map);
    }

    @ApiOperation(value = "根据讲师id查询讲师所讲课程列表", notes = "根据讲师id查询讲师所讲课程列表", httpMethod = "GET")
    @GetMapping("/getTeacherCourseById")
    public JsonResult getTeacherCourseById(@ApiParam(name = "id", value = "讲师id", required = true)
                                           @RequestParam("id") String id) {
        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);

        return JsonResult.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
