package com.lnzz.eduservice.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：EduSubjectOneVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/15 15:26
 * @Description 一级课程分类
 */
@Data
public class EduSubjectOneVo {

    private String id;

    private String title;

    private List<EduSubjectTwoVo> children = new ArrayList<>();
}
