package com.lnzz.eduservice.controller;


import com.lnzz.commonutils.JsonResult;
import com.lnzz.eduservice.pojo.vo.EduSubjectOneVo;
import com.lnzz.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@Api(value = "课程分类管理", tags = {"课程分类管理相关接口"})
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;


    @ApiOperation(value = "新增课程分类", notes = "新增课程分类", httpMethod = "POST")
    @PostMapping("/save")
    public JsonResult save(MultipartFile file) {

        eduSubjectService.saveSubject(file, eduSubjectService);
        return JsonResult.ok();
    }

    @ApiOperation(value = "课程分类列表", notes = "课程分类列表", httpMethod = "GET")
    @GetMapping("/list")
    public JsonResult list(){
       List<EduSubjectOneVo> list =  eduSubjectService.getAllSubject();
        return JsonResult.ok().data("list",list);
    }

}

