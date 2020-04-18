package com.lnzz.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：EduServiceApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/12 16:35
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class);
    }
}
