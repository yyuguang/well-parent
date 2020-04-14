package com.lnzz.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.EduTeacher;
import com.lnzz.eduservice.pojo.vo.EduTeacherQueryVo;
import com.lnzz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Api(value = "讲师管理", tags = {"讲师管理相关接口"})
public class EduTeacherController extends BaseController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有讲师列表", notes = "查询所有讲师列表", httpMethod = "GET")
    @GetMapping("/list")
    public JsonResult list() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return JsonResult.ok().data("items", list);
    }

    @ApiOperation(value = "新增讲师", notes = "新增讲师", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        return save ? JsonResult.ok() : JsonResult.error();
    }

    @ApiOperation(value = "编辑讲师", notes = "编辑讲师", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult update(@RequestBody EduTeacher eduTeacher) {
        boolean update = eduTeacherService.updateById(eduTeacher);
        return update ? JsonResult.ok() : JsonResult.error();
    }


    @ApiOperation(value = "根据id逻辑删除讲师", notes = "根据id逻辑删除讲师", httpMethod = "DELETE")
    @DeleteMapping("/delete/{id}")
    public JsonResult delete(@ApiParam(name = "id", value = "讲师id", required = true)
                                 @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        return flag ? JsonResult.ok() : JsonResult.error();
    }

    @ApiOperation(value = "讲师数据分页查询", notes = "讲师数据分页查询", httpMethod = "GET")
    @GetMapping("/page")
    public JsonResult page(@ApiParam(name = "pageNum", value = "页数", required = false)
                           @RequestParam("pageNum") Integer pageNum,
                           @ApiParam(name = "pageSize", value = "每页显示条数", required = false)
                           @RequestParam("pageSize") Integer pageSize) {

        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        Page<EduTeacher> page = new Page<>(pageNum, pageSize);

        eduTeacherService.page(page, null);

        long total = page.getTotal();
        List<EduTeacher> rows = page.getRecords();

        return JsonResult.ok().data("total", total).data("rows", rows);
    }

    @ApiOperation(value = "讲师数据条件分页查询", notes = "讲师数据条件分页查询", httpMethod = "GET")
    @PostMapping("/pageByKeys/{pageNum}/{pageSize}")
    public JsonResult pageByKeys(@ApiParam(name = "pageNum", value = "页数", required = false)
                                 @PathVariable Integer pageNum,
                                 @ApiParam(name = "pageSize", value = "每页显示条数", required = false)
                                 @PathVariable Integer pageSize,
                                 @RequestBody(required = false) EduTeacherQueryVo teacherQueryVo) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);

        eduTeacherService.pageByKeys(teacherPage, teacherQueryVo);

        long total = teacherPage.getTotal();
        List<EduTeacher> rows = teacherPage.getRecords();

        return JsonResult.ok().data("total", total).data("rows", rows);
    }

    @ApiOperation(value = "通过id获取单条记录", notes = "通过id获取单条记录", httpMethod = "GET")
    @GetMapping("/getById/{id}")
    public JsonResult getById(@ApiParam(name = "id", value = "讲师id", required = true)
                              @PathVariable String id) {
        EduTeacher items = eduTeacherService.getById(id);
        return JsonResult.ok().data("items", items);
    }

}

