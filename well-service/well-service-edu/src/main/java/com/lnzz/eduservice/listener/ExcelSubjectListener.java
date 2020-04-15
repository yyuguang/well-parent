package com.lnzz.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lnzz.eduservice.pojo.EduSubject;
import com.lnzz.eduservice.pojo.excel.ExcelSubjectData;
import com.lnzz.eduservice.service.EduSubjectService;
import com.lnzz.servicebase.exceptionhandler.WellParamException;

/**
 * ClassName：ExcelSubjectListener
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/15 14:22
 * @Description
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public ExcelSubjectListener() {
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null) {
            throw new WellParamException(20001, "文件数据为空");
        }

        EduSubject oneSubject = this.existOneSubject(eduSubjectService, excelSubjectData.getOneSubjectName());
        if (oneSubject == null) {
            oneSubject = new EduSubject();
            //没有相同一级分类，进行添加
            oneSubject.setParentId("0");
            oneSubject.setTitle(excelSubjectData.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }

        String parentId = oneSubject.getId();
        //添加二级分类
        EduSubject twoSubject = this.existTwoSubject(eduSubjectService, excelSubjectData.getTwoSubjectName(), parentId);
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(parentId);
            twoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            eduSubjectService.save(twoSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 判断一级分类不能重复添加
     *
     * @param eduSubjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        return eduSubjectService.getOne(wrapper);
    }

    /**
     * 判断二级分类不能重复添加
     *
     * @param eduSubjectService
     * @param name
     * @param parentId
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        return eduSubjectService.getOne(wrapper);
    }
}
