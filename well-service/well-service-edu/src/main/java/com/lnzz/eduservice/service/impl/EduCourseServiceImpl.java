package com.lnzz.eduservice.service.impl;

import com.lnzz.eduservice.pojo.EduCourse;
import com.lnzz.eduservice.mapper.EduCourseMapper;
import com.lnzz.eduservice.pojo.EduCourseDescription;
import com.lnzz.eduservice.pojo.vo.EduCourseInfoVo;
import com.lnzz.eduservice.service.EduCourseDescriptionService;
import com.lnzz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EduCourseDescriptionService descriptionService;

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
}
