package com.chery.auth.config.jap;

import com.chery.auth.client.constant.AuthServiceConstant;
import com.chery.common.util.StringUtils;
import com.fujieid.jap.ids.JapIds;
import com.fujieid.jap.ids.config.IdsConfig;
import com.fujieid.jap.ids.config.JwtConfig;
import com.fujieid.jap.ids.context.IdsContext;
import com.fujieid.jap.ids.filter.IdsAccessTokenFilter;
import com.fujieid.jap.ids.filter.IdsUserStatusFilter;
import com.fujieid.jap.ids.model.enums.TokenSigningAlg;
import com.fujieid.jap.ids.service.IdsClientDetailService;
import com.fujieid.jap.ids.service.IdsIdentityService;
import com.fujieid.jap.ids.service.IdsUserService;
import com.fujieid.jap.ids.util.JwkUtil;
import com.xkcoding.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2021-04-14 10:25
 * @since 1.0.0
 */
@Slf4j
@Component
@Configuration
public class JapIdsConfiguration implements ApplicationListener<ApplicationStartedEvent> {
    /**
     * 注入idsClientDetailService
     */
    @Autowired
    private IdsClientDetailService idsClientDetailService;
    /**
     * 注入IdsUserService
     */
    @Autowired
    private IdsUserService idsUserService;

    /**
     * 注入IdsIdentityService
     */
    @Autowired
    private IdsIdentityService idsIdentityService;

    /**
     * 程序启动完成后，注册 ids 的上下文
     *
     * @param applicationStartedEvent applicationStartedEvent
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        // 注册 JAP IDS 上下文
        String issuer = AuthServiceConstant.SERVICE_ADDRESS + AuthServiceConstant.AUTH_CONTEXT_PATH;
        JapIds.registerContext(new IdsContext()
                .setUserService(idsUserService)
                .setClientDetailService(idsClientDetailService)
                .setIdentityService(idsIdentityService)
                .setIdsConfig(new IdsConfig()
                        .setIssuer(issuer) //注释本段采用下面启动动态Issuer来进行
//                        .setEnableDynamicIssuer(true)
                        .setLoginUrl("/oauth/login")
                        .setJwtConfig(new JwtConfig()
                                .setJwksKeyId(AuthServiceConstant.AUTH_SERVER)
                                .setJwksJson(JwkUtil.createRsaJsonWebKeySetJson(AuthServiceConstant.AUTH_SERVER,
                                        TokenSigningAlg.RS256))
                        )
                )
        );
        log.info(JsonUtil.toJsonString(JapIds.getIdsConfig()));
        // 配置 ids 支持的 scope, 系统默认支持以下 scope： read、write、openid、email、phone
        // 如果需要追加 scope，可以使用 addScope
//        IdsScopeProvider.addScope(new IdsScope().setCode("test").setDescription("test"));
    }

    /**
     * Access Token 验权过滤器
     * @return Access Token 验权过滤器
     */
    @Bean
    public FilterRegistrationBean<IdsAccessTokenFilter> registerAccessTokenFilter() {
        FilterRegistrationBean<IdsAccessTokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new IdsAccessTokenFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("ignoreUrl",
                StringUtils.join(AuthServiceConstant.releaseWhitelist, ","));
        registration.setName("IdsAccessTokenFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 用户登录状态过滤器
     * @return 用户登录状态过滤器
     */
    @Bean
    public FilterRegistrationBean<IdsUserStatusFilter> registerUserStatusFilter() {
        FilterRegistrationBean<IdsUserStatusFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new IdsUserStatusFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("ignoreUrl",
                StringUtils.join(AuthServiceConstant.releaseWhitelist, ","));
        registration.setName("IdsUserStatusFilter");
        registration.setOrder(1);
        return registration;
    }
}
