package com.lnzz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.eduservice.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lnzz.eduservice.pojo.vo.EduTeacherQueryVo;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-12
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 条件分页查询
     *
     * @param pageParam
     * @param teacherQueryVo
     */
    void pageByKeys(Page<EduTeacher> pageParam, EduTeacherQueryVo teacherQueryVo);

    /**
     * 查询热门讲师
     * @return
     */
    List<EduTeacher> selectHotTeacher();
}
