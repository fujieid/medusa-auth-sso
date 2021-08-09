/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: AuthServerApplication
 * Author:   Allen
 * Date:     2021/7/30
 * Description: AuthServer的启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * AuthServer的启动类
 *
 * @author Allen
 * @date 2021-07-30
 */
@Slf4j
@RefreshScope
@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServerApplication {
    /**
     * 主方法
     *
     * @param args spring boot参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功!  ლ(´ڡ`ლ)ﾞ");
    }
}
