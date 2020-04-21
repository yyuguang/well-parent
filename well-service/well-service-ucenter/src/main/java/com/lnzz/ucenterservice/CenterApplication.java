package com.lnzz.ucenterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：CenterApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 16:47
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
public class CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class, args);
    }

}
