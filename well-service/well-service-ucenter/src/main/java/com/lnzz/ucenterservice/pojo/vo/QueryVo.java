package com.lnzz.ucenterservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName：QueryVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/22 9:35
 * @Description
 */
@Data
@ApiModel(value = "模糊搜索", description = "模糊搜索")
public class QueryVo {
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
