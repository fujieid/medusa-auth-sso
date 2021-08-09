/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: indexController
 * Author:   zhangzhicheng
 * Date:     2020/09/10
 * Description: 登录成功跳转页面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.rest.controller;

import com.chery.auth.core.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

/**
 * 登录成功携参跳转页面
 * freemarker不可使用@RestController注解，否则页面无法请求到.
 *
 * @author zhangzhicheng
 * @date 2020-09-10
 */
@Slf4j
@Controller
@ApiIgnore
@SessionAttributes("userName")
@RequestMapping("/")
public class IndexController {

    /**
     * 注入userService
     */
    @Autowired
    private UserServiceImpl userService;
    /**
     * 主页面跳转
     * @param model mvcModel
     * @param user 登陆用户
     * @return 主页面跳转
     */
    @GetMapping(value = {"/index", "/loginSuccess"})
    public String  index(Model model,  Principal user) {
        model.addAttribute("userName", userService.getByUsername(user.getName()).getUsername());
        return "index";
    }

}
