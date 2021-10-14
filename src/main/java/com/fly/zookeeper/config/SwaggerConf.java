package com.fly.zookeeper.config;

import io.swagger.models.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fly
 * @version 1.0
 * @date 2021/10/14 22:48
 * @description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.enable(true)
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("zookeeper接口测试")
                .description("接口说明")
                .termsOfServiceUrl("http://localhost:8080")
                .version("1.0")
                .build();
    }
}
