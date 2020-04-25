package com.lnzz.orderservice.client;

import com.lnzz.commonutils.ordervo.EduCourseOrderVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName：EduClient
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/25 18:00
 * @Description
 */
@Component
@FeignClient("/eduservice")
public interface EduClient {

    /**
     * 订单根据课程id获取课程信息
     *
     * @param id
     * @return
     */
    @PostMapping("/eduservice/course-front/getCourseInfoOrder")
    EduCourseOrderVo getCourseInfoOrder(@ApiParam(name = "id", value = "课程id", required = true)
                                        @RequestParam("id") String id);
}
