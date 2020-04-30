package com.lnzz.aclservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName：AclConfig
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/29 12:12
 * @Description
 */
@Configuration
@MapperScan("com.lnzz.aclservice.mapper")
public class AclConfig {
    /**
     * 逻辑删除插件
     *
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

