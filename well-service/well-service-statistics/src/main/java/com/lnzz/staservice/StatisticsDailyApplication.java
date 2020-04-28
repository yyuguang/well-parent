package com.lnzz.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName：StatisticsDailyApplication
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/27 21:07
 * @Description
 */
@SpringBootApplication
@MapperScan("com.lnzz.staservice.mapper")
@ComponentScan("com.lnzz")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StatisticsDailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsDailyApplication.class, args);
    }
}
