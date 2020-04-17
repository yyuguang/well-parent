package com.lnzz.eduservice.service;

import com.lnzz.eduservice.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.eduservice.pojo.vo.EduChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 通过课程id获取章节小节list
     *
     * @param courseId
     * @return
     */
    List<EduChapterVo> getChapterAndVideoListByCourseId(String courseId);

    /**
     * 删除章节
     *
     * @param chapterId
     * @return
     */
    boolean deleteChapterById(String chapterId);

    /**
     * 根据课程id删除所有章节
     * @param courseId
     */
    void removeChapterByCourseId(String courseId);
}
