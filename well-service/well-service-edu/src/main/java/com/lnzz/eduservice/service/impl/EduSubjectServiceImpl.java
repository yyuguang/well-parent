package com.lnzz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.eduservice.listener.ExcelSubjectListener;
import com.lnzz.eduservice.pojo.EduSubject;
import com.lnzz.eduservice.mapper.EduSubjectMapper;
import com.lnzz.eduservice.pojo.excel.ExcelSubjectData;
import com.lnzz.eduservice.pojo.vo.EduSubjectOneVo;
import com.lnzz.eduservice.pojo.vo.EduSubjectTwoVo;
import com.lnzz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 冷暖自知
 * @since 2020-04-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream,
                    ExcelSubjectData.class,
                    new ExcelSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EduSubjectOneVo> getAllSubject() {

        //1.查询所有一级分类 parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");

        List<EduSubject> eduSubjectOneList = baseMapper.selectList(wrapperOne);

        //2.查询所有二级分类 parent_id != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");

        List<EduSubject> eduSubjectTwoList = baseMapper.selectList(wrapperTwo);

        List<EduSubjectOneVo> finalSubjectList = new ArrayList<>();

        //3.封装一级分类
        for (int i = 0; i < eduSubjectOneList.size(); i++) {
            EduSubject eduSubject = eduSubjectOneList.get(i);
            EduSubjectOneVo eduSubjectOneVo = new EduSubjectOneVo();
            BeanUtils.copyProperties(eduSubject, eduSubjectOneVo);
            finalSubjectList.add(eduSubjectOneVo);

            //4.封装二级分类
            List<EduSubjectTwoVo> finalTwoSubjectList = new ArrayList<>();
            for (int j = 0; j < eduSubjectTwoList.size(); j++) {
                EduSubject eduTwoSubject = eduSubjectTwoList.get(j);
                if (eduTwoSubject.getParentId().equals(eduSubject.getId())) {
                    EduSubjectTwoVo eduSubjectTwoVo = new EduSubjectTwoVo();
                    BeanUtils.copyProperties(eduTwoSubject, eduSubjectTwoVo);
                    finalTwoSubjectList.add(eduSubjectTwoVo);
                }
            }
            eduSubjectOneVo.setChildren(finalTwoSubjectList);

        }


        return finalSubjectList;
    }
}
