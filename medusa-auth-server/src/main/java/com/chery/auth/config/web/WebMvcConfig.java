/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: WebMvcConfig
 * Author:   Allen
 * Date:     2020/8/15
 * Description: spring mvc配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring mvc配置
 *
 * @author Allen
 * @date 2020-07-10
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册绑定/login和login.ftl视图模板
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/user_info.html").setViewName("user/user_info");
        registry.addViewController("/client_list.html").setViewName("client/client_list");
    }
}
