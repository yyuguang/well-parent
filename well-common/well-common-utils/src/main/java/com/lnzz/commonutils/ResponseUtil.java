package com.lnzz.commonutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName：ResponseUtil
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 14:29
 * @Description
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, JsonResult result) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
