package com.lnzz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.eduservice.pojo.vo.EduCourseInfoVo;
import com.lnzz.eduservice.pojo.vo.EduCoursePublishVo;
import com.lnzz.eduservice.pojo.vo.EduCourseQueryVo;

import java.util.List;

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

    /**
     * 课程列表分页并带模糊查询
     *
     * @param coursePage
     * @param queryVo
     */
    void pageByKeys(Page<EduCourse> coursePage, EduCourseQueryVo queryVo);

    /**
     * 删除课程
     *
     * @param courseId
     * @return
     */
    boolean removeCourse(String courseId);

    /**
     * 查询热门课程
     *
     * @return
     */
    List<EduCourse> selectHotCourse();
}
