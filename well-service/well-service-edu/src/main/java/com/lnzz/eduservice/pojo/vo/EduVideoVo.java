package com.lnzz.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName：EduVideoVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/16 14:33
 * @Description
 */
@Data
public class EduVideoVo {

    private String id;

    private String title;

    private String videoSourceId;

    private Boolean isFree;
}
