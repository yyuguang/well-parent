package com.lnzz.eduservice.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * ClassName：ExcelSubjectData
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/15 14:18
 * @Description
 */
@Data
public class ExcelSubjectData {

    /**
     * 一级分类
     */
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    /**
     * 二级分类
     */
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
