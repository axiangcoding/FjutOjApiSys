package com.fjut.oj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 注解 @EnableSwagger2 启动Swagger
 * @author axiang [20190704] swagger配置文件
 */
//@EnableSwagger2
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.fjut.oj.controller")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Online Judge项目接口文档")
                .description("Online Judge项目接口测试")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}
