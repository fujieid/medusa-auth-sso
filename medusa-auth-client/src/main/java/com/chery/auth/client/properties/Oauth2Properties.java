/*
 * Copyright (C), 2020, 安徽艾伦家居饰品有限公司
 * FileName: Oauth2Properties
 * Author:   Allen
 * Date:     2020/8/17
 * Description: oauth2配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.properties;

import com.chery.common.core.constant.CommonConstants;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * oauth2配置
 *
 * @author Allen
 * @date 2020/8/17
 */
@Data
@Configuration
@EnableConfigurationProperties({Oauth2Properties.class})
@ConfigurationProperties(prefix = "oauth.token")
public class Oauth2Properties {
    /**
     * clientId
     */
    private String clientId;
    /**
     * clientSecret
     */
    private String clientSecret;

    /**
     * 获取basic认证签名串
     * 
     * @return base64编码字符串，用于basic认证
     */
    public String basicAuth() {
        Assert.notNull(clientId, "oauth.token.clientId不能为空");
        Assert.notNull(clientSecret, "oauth.token.clientSecret不能为空");
        String auth = clientId + ":" + clientSecret;
        return "Basic " + Base64.encodeBase64String(auth.getBytes(CommonConstants.DEFAULT_CHARSET));
    }
}
