package com.lnzz.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.commonutils.ordervo.EduCourseOrderVo;
import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.pojo.vo.EduChapterVo;
import com.lnzz.eduservice.pojo.vo.EduCourseFrontQueryVo;
import com.lnzz.eduservice.pojo.vo.EduCourseFrontWebVo;
import com.lnzz.eduservice.service.EduChapterService;
import com.lnzz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName：CourseFrontController
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/23 21:15
 * @Description
 */
@RestController
@RequestMapping("/eduservice/course-front")
@Api(value = "前端课程接口管理", tags = {"前端课程接口管理相关接口"})
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "课程分页查询", notes = "课程分页查询", httpMethod = "POST")
    @PostMapping("/coursePageList/{pageNum}/{pageSize}")
    public JsonResult coursePageList(@ApiParam(name = "pageNum", value = "页数", required = true)
                                     @PathVariable("pageNum") Integer pageNum,
                                     @ApiParam(name = "pageSize", value = "每页显示条数", required = true)
                                     @PathVariable("pageSize") Integer pageSize,
                                     @ApiParam(name = "queryVo", value = "查询对象", required = false)
                                     @RequestBody(required = false) EduCourseFrontQueryVo queryVo) {

        Page<EduCourse> pageParam = new Page<EduCourse>(pageNum, pageSize);

        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, queryVo);

        return JsonResult.ok().data(map);
    }

    @ApiOperation(value = "课程分页查询", notes = "课程分页查询", httpMethod = "GET")
    @GetMapping("/getCourseInfoById")
    public JsonResult getCourseInfoById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @RequestParam("courseId") String courseId) {
        //查询课程信息和讲师信息
        EduCourseFrontWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<EduChapterVo> chapterVoList = eduChapterService.getChapterAndVideoListByCourseId(courseId);

        return JsonResult.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

    @ApiOperation(value = "订单根据课程id获取课程信息", notes = "订单根据课程id获取课程信息", httpMethod = "POST")
    @PostMapping("/getCourseInfoOrder")
    public EduCourseOrderVo getCourseInfoOrder(@ApiParam(name = "id", value = "课程id", required = true)
                                               @RequestParam("id") String id) {
        EduCourseFrontWebVo webVo = eduCourseService.selectInfoWebById(id);
        EduCourseOrderVo orderVo = new EduCourseOrderVo();
        BeanUtils.copyProperties(webVo, orderVo);
        return orderVo;
    }
}
