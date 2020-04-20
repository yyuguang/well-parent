package com.lnzz.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：CmsApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/19 15:31
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
