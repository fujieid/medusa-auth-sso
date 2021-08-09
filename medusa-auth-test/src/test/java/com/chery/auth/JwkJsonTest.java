/*
 * Copyright (C), 2021, Chery Commercial Vehicle (Anhui) Co. Ltd
 * FileName: JwkJsonTest
 * Author:   Allen
 * Date:     2021/8/2
 * Description: 密码测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth;

import com.chery.auth.client.constant.AuthServiceConstant;
import com.chery.common.util.StringUtils;
import com.fujieid.jap.ids.model.enums.TokenSigningAlg;
import com.fujieid.jap.ids.util.JwkUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 〈密码测试类〉
 *
 * @author Allen
 * @date 2021/8/2
 * @since 1.0.0
 */
@Slf4j
public class JwkJsonTest {

    /**
     * 测试加密后的密码串
     */
    @Test
    public void getStringJoin() {
        String json = StringUtils.join(AuthServiceConstant.releaseWhitelist, ",");
        log.info("json--{}", json);
    }

    /**
     * 测试加密后的密码串
     */
    @Test
    public void getJwkJson() {
        String jwkJsonRsa = JwkUtil.createRsaJsonWebKeySetJson(AuthServiceConstant.AUTH_SERVER, TokenSigningAlg.RS256);
        String jwkJsonEs = JwkUtil.createEsJsonWebKeySetJson(AuthServiceConstant.AUTH_SERVER, TokenSigningAlg.ES256);
        log.info("jsonRsa--{}", jwkJsonRsa);
        log.info("jsonEs--{}", jwkJsonEs);
        jwkJsonRsa = JwkUtil.createRsaJsonWebKeyJson(AuthServiceConstant.AUTH_SERVER, TokenSigningAlg.RS256);
        log.info("jsonRsaAs--{}", jwkJsonRsa);
    }
}


