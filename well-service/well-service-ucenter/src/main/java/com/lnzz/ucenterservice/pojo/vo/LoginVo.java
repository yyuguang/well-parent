package com.lnzz.ucenterservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName：LoginVo
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 16:55
 * @Description
 */
@Data
@ApiModel(value = "登录对象", description = "登录对象")
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
