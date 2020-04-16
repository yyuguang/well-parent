package com.lnzz.eduservice.pojo.vo;

import lombok.Data;

/**
 * ClassName：EduCoursePublishVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/16 20:19
 * @Description
 */
@Data
public class EduCoursePublishVo {

    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;
}
