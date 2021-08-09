/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientQuery
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 客户端信息上传入参
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端信息上传入参
 *
 * @author Allen
 * @date 2020-07-10
 */
@Data
public class ClientQuery implements Serializable {
    /**
     * 用于客户端唯一标识 *
     */
    @ApiParam(value = "客户端ID", example = "app")
    private String clientId;

    /**
     * 客户端密钥
     */
    @ApiParam(value = "客户端密钥", example = "123")
    private String clientSecret;

    /**
     * 服务ID
     */
    @ApiParam(value = "服务ID", example = "openapi-service")
    private String resourceIds;

    /**
     * 客户端访问域
     */
    @ApiParam(value = "客户端访问域", example = "read,write")
    private String scope;

    /**
     * oauth授权类型
     */
    @ApiParam(value = "授权类型", example = "authorization_code")
    private String authorizedGrantTypes;


    /**
     * access_token初始超期时间
     */
    @ApiParam(value = "初始超期时间(秒)", example = "36000")
    private String accessTokenValidity;
}
