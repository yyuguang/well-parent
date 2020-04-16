package com.lnzz.eduservice.service;

import com.lnzz.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.eduservice.pojo.vo.EduCourseInfoVo;
import com.lnzz.eduservice.pojo.vo.EduCoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(EduCourseInfoVo courseInfoVo);

    /**
     * 根据课程id获取课程信息
     *
     * @param courseId
     * @return
     */
    EduCourseInfoVo getCourseInfo(String courseId);

    /**
     * 编辑课程信息
     *
     * @param eduCourseInfoVo
     */
    void updateCourseInfo(EduCourseInfoVo eduCourseInfoVo);

    /**
     * 根据课程id获取课程发布信息
     *
     * @param courseId
     * @return
     */
    EduCoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * 课程发布
     *
     * @param courseId
     * @return
     */
    boolean publishCourseById(String courseId);
}
