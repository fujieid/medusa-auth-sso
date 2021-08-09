/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientDTO
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 客户端DTO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端DTO
 * @author Allen
 * @date 2020-07-10
 */
@Data
public class ClientDTO implements Serializable {
    /**
     * 用于客户端唯一标识 *
     */
    @ApiModelProperty(value = "客户端ID", example = "app", required = true)
    private String clientId;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID", example = "openapi-service", position = 1)
    private String resourceIds;

    /**
     * 客户端密码
     */
    @ApiModelProperty(value = "客户端密码", example = "apppassword", position = 2)
    private String clientSecret;

    /**
     * 客户端访问域
     */
    @ApiModelProperty(value = "客户端访问域", example = "read,write", position = 3)
    private String scope;

    /**
     * oauth授权类型
     */
    @ApiModelProperty(value = "oauth授权类型", example = "authorization_code", position = 4)
    private String authorizedGrantTypes;

    /**
     * 跳转URI
     */
    @ApiModelProperty(value = "跳转URI", example = "http://****", position = 5)
    private String webServerRedirectUri;

    /**
     * 角色编码，多个用“,”连接
     */
    @ApiModelProperty(value = "角色编码", example = "ADMIN,USER", position = 6)
    private String authorities;

    /**
     * access_token初始超期时间
     */
    @ApiModelProperty(value = "access_token初始超期时间", example = "31536000", position = 7)
    private String accessTokenValidity;

    /**
     * 刷新token初始超期时间
     */
    @ApiModelProperty(value = "刷新token初始超期时间", example = "31536000", position = 8)
    private String refreshTokenValidity;

    /**
     * 附加信息
     */
    @ApiModelProperty(value = "附加信息", example = "app客户端", position = 9)
    private String additionalInformation;

    /**
     * 自动批准
     */
    @ApiModelProperty(value = "自动批准", example = "false", position = 10)
    private Boolean autoApprove;

    /**
     * 应用程序的状态，当它为假时，不允许登录
     */
    @ApiModelProperty(value = "应用程序的状态", example = "true", position = 11)
    private Boolean available;

    /** 可绑定 **/
    @ApiModelProperty(value = "可绑定", example = "true", position = 12)
    private Boolean bind;
}
