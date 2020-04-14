package com.lnzz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnzz.eduservice.pojo.EduTeacher;
import com.lnzz.eduservice.mapper.EduTeacherMapper;
import com.lnzz.eduservice.pojo.vo.EduTeacherQueryVo;
import com.lnzz.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageByKeys(Page<EduTeacher> pageParam, EduTeacherQueryVo teacherQueryVo) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("gmt_create");

        if (teacherQueryVo == null) {
            baseMapper.selectPage(pageParam, wrapper);
            return;
        }
        //多条件组合查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        baseMapper.selectPage(pageParam, wrapper);
    }
}
