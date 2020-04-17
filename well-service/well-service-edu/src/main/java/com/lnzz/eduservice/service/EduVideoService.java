package com.lnzz.eduservice.service;

import com.lnzz.eduservice.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除所有小节
     *
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);
}
