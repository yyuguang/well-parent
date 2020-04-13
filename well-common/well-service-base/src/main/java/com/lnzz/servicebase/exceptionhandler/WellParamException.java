package com.lnzz.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName：WellParamException
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/13 19:11
 * @Description 统一异常处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WellParamException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;
}
