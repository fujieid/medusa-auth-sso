/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: SwaggerConfig
 * Author:   Allen
 * Date:     2020/8/15
 * Description: swagger2.0配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger3.0配置
 * 因此项目设置仅登录后才可调用相关接口，故而此处未swagger设置BearerToken!
 * @author Allen
 * @date 2020-08-10
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /**
     * 创建Docket对象
     * 
     * @return 返回Docket对象
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建ApiInfo对象
     * 
     * @return 返回ApiInfo对象
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("艾伦(良帅)个人版权所有", "http://www.gitee.com/apicescn",
                "apicescn@hotmail.com");
        return new ApiInfoBuilder().contact(contact).title("HaoYu Cloud Auth")
                .termsOfServiceUrl("https://blog.csdn.net/apicescn")
                .description("Auth Server接口文档").version("1.2.1").build();
    }
}
