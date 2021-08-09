/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ServiceListConstant
 * Author:   Allen
 * Date:     2020/8/19
 * Description: 服务列表接口常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.constant;

/**
 * 服务列表接口常量
 *
 * @author Allen
 * @date 2020-09-20
 */
public class AuthServiceConstant {
    /**
     * 服务地址
     */
    public static final String SERVICE_ADDRESS = "http://localhost:7777";
    /**
     * 默认clientId
     */
    public static final String DEFAULT_CLIENT_ID = "auth";

    /**
     * Auth服务
     */
    public static final String AUTH_SERVER = "medusa-auth-server";

    /**
     * Auth服务的CONTEXT_PATH
     */
    public static final String AUTH_CONTEXT_PATH = "/auth";

    /**
     * Auth服务操作类型
     */
    public static final String AUTH_MODEL_TYPE = "edit";

    /**
     * 是个人中心保存还是用户管理中心用户保存
     */
    public static final String SAVE_INFO_TYPE = "info";

    /**
     * 允许访问地址列表
     */
    public static final String[] releaseWhitelist = {
            "/",
            "/oauth/login",
            "/oauth/login/customize",
            "/oauth/logout",
            "/oauth/error",
            "/oauth/confirm",
            "/oauth/confirm/customize",
            "/oauth/authorize",
            "/oauth/authorize/auto",
            "/oauth/token",
            "/oauth/check_session",
            "/oauth/registration",
            "/oauth/pkce/**",
            "/.well-known/jwks.json",
            "/.well-known/openid-configuration"
    };
}
