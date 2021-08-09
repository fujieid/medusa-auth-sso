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

import com.fujieid.jap.ids.endpoint.LogoutEndpoint;
import com.fujieid.jap.ids.model.IdsResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2020-07-10
 */
@ApiIgnore
@RestController
@RequestMapping("/oauth")
public class UserLogoutController {

    /**
     * 用户登出
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return 用户登出
     */
    @GetMapping(value = "/logout", produces = {MediaType.APPLICATION_JSON_VALUE})
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        IdsResponse<String, String> idsResponse = new LogoutEndpoint().logout(request, response);
        return new RedirectView(idsResponse.getData());
    }
}
