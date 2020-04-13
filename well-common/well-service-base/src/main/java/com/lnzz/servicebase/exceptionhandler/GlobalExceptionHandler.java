package com.lnzz.servicebase.exceptionhandler;

import com.lnzz.commonutils.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName：GlobalExceptionHandler
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/12 21:36
 * @Description
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult error(Exception e) {
        e.printStackTrace();
        return JsonResult.error().message("执行了全局异常处理..");
    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(WellParamException.class)
    @ResponseBody
    public JsonResult error(WellParamException e) {
        e.printStackTrace();
        return JsonResult.error().code(e.getCode()).message(e.getMsg());
    }


}
