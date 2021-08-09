/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientDO
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 客户端信息实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端信息实体
 *
 * @author Allen
 * @date 2020-07-10
 */
@Data
@TableName("oauth_client_details")
public class ClientDO implements Serializable {
    /**
     * 自增Id
     */
    @TableId
    private String id;

    /** 用于客户端唯一标识 */
    private String clientId;

    /** 服务ID */
    private String resourceIds;

    /** 客户端密码 */
    private String clientSecret;

    /** 客户端访问域 */
    @TableField(value = "scope")
    private String scopes;

    /** 授权类型-同authorizedGrantTypes */
    @TableField(value = "authorized_grant_types",condition = SqlCondition.LIKE)
    private String grantTypes;

    /** oauth授权类型 */
    @TableField(condition = SqlCondition.LIKE)
    private String authorizedGrantTypes;

    /** 跳转URI-同webServerRedirectUri*/
    @TableField(value = "web_server_redirect_uri")
    private String redirectUri;

    /** 跳转URI */
    private String webServerRedirectUri;

    /** 角色编码，多个用“,”连接 */
    private String authorities;

    /** access_token初始超期时间 */
    private String accessTokenValidity;

    /** 刷新token初始超期时间 */
    private String refreshTokenValidity;

    /** 附加信息 */
    private String additionalInformation;

    /** 应用程序的状态，当它为假时，不允许登录 */
    private Boolean available;

    /** 自动批准 */
    private Boolean autoApprove;

    /** 可绑定 **/
    private Boolean bind;
}
