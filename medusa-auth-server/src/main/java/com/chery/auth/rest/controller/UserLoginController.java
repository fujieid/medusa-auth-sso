/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: AuthUserControllerImpl
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 获取用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.rest.controller;

import com.chery.auth.client.properties.Oauth2Properties;
import com.fujieid.jap.ids.endpoint.LoginEndpoint;
import com.fujieid.jap.ids.model.IdsConsts;
import com.fujieid.jap.ids.model.IdsResponse;
import io.undertow.servlet.spec.HttpServletRequestImpl;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MimeHeaders;
import java.io.IOException;
import java.util.Map;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2020-07-10
 */
@Slf4j
@ApiIgnore
@Controller
@RequestMapping("/oauth")
public class UserLoginController {
    /**
     * oauth2配置
     */
    @Resource
    private Oauth2Properties oauth2Properties;

    /**
     * get请求的用户登出
     * @param request request
     * @param response response
     * @throws IOException
     */
    @GetMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new LoginEndpoint().showLoginPage(request, response);
    }

    /**
     * post请求的用户登录
     * @param request request
     * @param response response
     * @return 用户登录
     */
    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RedirectView login(HttpServletRequest request, HttpServletResponse response) {
        //begin 本段为从yaml配置文件中获得OAuth的配置信息，然后添加到Header中.
        String basicAuth = oauth2Properties.basicAuth();
        HttpServletRequestImpl httpServletRequest = (HttpServletRequestImpl)request;
        HeaderMap headerMap = httpServletRequest.getExchange().getRequestHeaders();
        headerMap.add(new HttpString(IdsConsts.AUTHORIZATION_HEADER_NAME), basicAuth);
        //end 处理完毕
        IdsResponse<String, String> idsResponse = new LoginEndpoint().signin(request, response);
        return new RedirectView(idsResponse.getData());
    }
}
