package com.lnzz.eduservice.service;

import com.lnzz.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.eduservice.pojo.vo.EduSubjectOneVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     *
     * @param file
     * @param eduSubjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    /**
     * 获取所有一级、二级课程分类
     * @return
     */
    List<EduSubjectOneVo> getAllSubject();
}
