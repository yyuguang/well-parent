package com.lnzz.aclservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName：AclApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 12:10
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lnzz"})
@EnableDiscoveryClient
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class);
    }
}
