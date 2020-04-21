package com.lnzz.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：MsmApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/21 15:06
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
public class MsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }

}
