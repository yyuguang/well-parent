package com.lnzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.mapper.EduCourseMapper;
import com.lnzz.eduservice.pojo.EduCourseDescription;
import com.lnzz.eduservice.pojo.vo.*;
import com.lnzz.eduservice.service.EduChapterService;
import com.lnzz.eduservice.service.EduCourseDescriptionService;
import com.lnzz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.eduservice.service.EduVideoService;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    /**
     * 已发布
     */
    private static final String COURSE_NORMAL = "Normal";

    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(EduCourseInfoVo courseInfoVo) {

        //1.向课程表添加基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result == 0) {
            throw new WellParamException(20001, "添加课程基本信息失败");
        }

        //一对一关系，id一致
        String eduCourseId = eduCourse.getId();

        //2.向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(eduCourseId);
        courseDescription.setDescription(courseInfoVo.getDescription());
        descriptionService.save(courseDescription);
        return eduCourseId;
    }

    @Override
    public EduCourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseInfoVo courseInfoVo = new EduCourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        EduCourseDescription courseDescription = descriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateCourseInfo(EduCourseInfoVo eduCourseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseInfoVo, eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if (result == 0) {
            throw new WellParamException(20001, "修改课程信息失败");
        }
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(eduCourseInfoVo.getId());
        courseDescription.setDescription(eduCourseInfoVo.getDescription());
        descriptionService.updateById(courseDescription);
    }

    @Override
    public EduCoursePublishVo getPublishCourseInfo(String courseId) {
        return baseMapper.getPublishCourseInfo(courseId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean publishCourseById(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus(COURSE_NORMAL);
        int result = baseMapper.updateById(eduCourse);
        return result > 0;
    }

    @Override
    public void pageByKeys(Page<EduCourse> coursePage, EduCourseQueryVo queryVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (queryVo == null) {
            baseMapper.selectPage(coursePage, queryWrapper);
            return;
        }

        String title = queryVo.getTitle();
        String teacherId = queryVo.getTeacherId();
        String subjectParentId = queryVo.getSubjectParentId();
        String subjectId = queryVo.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }
        baseMapper.selectPage(coursePage, queryWrapper);

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean removeCourse(String courseId) {

        eduVideoService.removeVideoByCourseId(courseId);

        eduChapterService.removeChapterByCourseId(courseId);

        descriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);
        return result > 0;
    }

    @Override
    public List<EduCourse> selectHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //根据id倒序查询，实际根据公司业务
        wrapper.eq("status", "Normal");
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<EduCourse> selectByTeacherId(String teacherId) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, EduCourseFrontQueryVo queryVo) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "Normal");
        if (!StringUtils.isEmpty(queryVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", queryVo.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(queryVo.getSubjectId())) {
            queryWrapper.eq("subject_id", queryVo.getSubjectId());
        }

        if (!StringUtils.isEmpty(queryVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(queryVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(queryVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public EduCourseFrontWebVo selectInfoWebById(String id) {
        this.updatePageViewCount(id);
        return baseMapper.selectInfoWebById(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }
}
