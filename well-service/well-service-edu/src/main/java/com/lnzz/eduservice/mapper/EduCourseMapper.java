package com.lnzz.eduservice.mapper;

import com.lnzz.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lnzz.eduservice.pojo.vo.EduCoursePublishVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据课程id获取课程发布信息
     *
     * @param courseId
     * @return
     */
    EduCoursePublishVo getPublishCourseInfo(@Param("courseId") String courseId);
}
