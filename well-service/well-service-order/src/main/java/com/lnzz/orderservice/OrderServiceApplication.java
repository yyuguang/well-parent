package com.lnzz.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：OrderServiceApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/26 10:28
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class);
    }

}
