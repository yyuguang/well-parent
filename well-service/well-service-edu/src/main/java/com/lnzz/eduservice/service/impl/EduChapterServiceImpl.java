package com.lnzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.eduservice.pojo.EduChapter;
import com.lnzz.eduservice.mapper.EduChapterMapper;
import com.lnzz.eduservice.pojo.EduVideo;
import com.lnzz.eduservice.pojo.vo.EduChapterVo;
import com.lnzz.eduservice.pojo.vo.EduVideoVo;
import com.lnzz.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnzz.eduservice.service.EduVideoService;
import com.lnzz.servicebase.exceptionhandler.WellParamException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<EduChapterVo> getChapterAndVideoListByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperChapter);

        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> videoList = eduVideoService.list(wrapperVideo);

        List<EduChapterVo> finalList = new ArrayList<>();


        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter eduChapter = chapterList.get(i);
            EduChapterVo chapterVo = new EduChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            List<EduVideoVo> eduVideoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo eduVideo = videoList.get(j);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    EduVideoVo eduVideoVo = new EduVideoVo();
                    BeanUtils.copyProperties(eduVideo, eduVideoVo);
                    eduVideoVoList.add(eduVideoVo);
                }
            }
            chapterVo.setChildren(eduVideoVoList);
        }

        return finalList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(videoWrapper);
        if (count > 0) {
            throw new WellParamException(20001, "删除失败，该章节下存在小节");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }
}
