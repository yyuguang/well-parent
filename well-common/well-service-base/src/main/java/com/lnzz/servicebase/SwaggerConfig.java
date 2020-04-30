package com.lnzz.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName：SwaggerConfig
 *
 * @author 冷暖自知
 * @version 1.0
 * @date 2020/4/12 16:57
 * @Description
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("学成教育 - 课程中心API文档")
                .contact(new Contact("lnzz",
                        "http://114.116.224.114",
                        "lengnzz@yeah.net"))
                .description("专为学成教育提供的api文档")
                .version("1.0.1")
                .termsOfServiceUrl("http://47.94.200.204")
                .build();
    }
}
