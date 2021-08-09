/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: MybatisPlusConfig
 * Author:   Allen
 * Date:     2020/8/17 17:21
 * Description: MybatisPlus配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈MybatisPlus配置类〉
 *
 * @author Allen
 * @create 2020/8/17
 * @since 1.0.0
 */
@Configuration
@MapperScan({MybatisPlusConfig.MEDUSA_MDAO_PACKAGE})
public class MybatisPlusConfig {

    /**
     * 平台dao包名
     */
    static final String MEDUSA_MDAO_PACKAGE = "com.*.*.core.dao";

    /**
     * 分页插件
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     * @return 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}
