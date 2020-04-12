package com.lnzz.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName：EduTeacherQueryVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/12 20:24
 * @Description
 */
@Data
public class EduTeacherQueryVo {

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
