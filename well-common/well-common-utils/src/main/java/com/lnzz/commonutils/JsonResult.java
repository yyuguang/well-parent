package com.lnzz.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName：JsonResult
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/12 19:32
 * @Description
 */
@Data
public class JsonResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private JsonResult() {

    }

   
    public static JsonResult ok() {
        JsonResult json = new JsonResult();
        json.setSuccess(true);
        json.setCode(ResultCode.SUCCESS);
        json.setMessage("成功");
        return json;
    }


    public static JsonResult error() {
        JsonResult json = new JsonResult();
        json.setSuccess(false);
        json.setCode(ResultCode.ERROR);
        json.setMessage("失败");
        return json;
    }

    public JsonResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public JsonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public JsonResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public JsonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public JsonResult data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
