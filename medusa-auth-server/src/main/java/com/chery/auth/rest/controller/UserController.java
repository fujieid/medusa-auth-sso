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

import com.chery.auth.client.constant.AuthServiceConstant;
import com.chery.auth.client.query.UserSaveQuery;
import com.chery.auth.client.query.UserUpdatePassQuery;
import com.chery.auth.core.entity.UserDO;
import com.chery.auth.core.service.impl.UserServiceImpl;
import com.chery.common.util.StringUtils;
import com.fujieid.jap.ids.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2020-07-10
 */
@ApiIgnore
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 注入userService
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * 系统用户管理
     * @param model model
     * @return 系统用户管理
     */
    @GetMapping(value = "/manager")
    public String userManager(Model model) {
        List<UserDO> userDOList = userService.listAll();
        model.addAttribute("userList", userDOList);
        return "user/user_manager";
    }

    /**
     * 根据userId查询用户信息
     *
     * @param modelType 类型（查询还是编辑）
     * @param userId  用户ID
     * @return 用户信息
     */
    @GetMapping(value = "/view")
    public ModelAndView getUserById(String userId, String modelType) {
        UserInfo userInfo = userService.getById(userId);
        ModelAndView view = new ModelAndView();
        view.addObject("user", userInfo);
        if (StringUtils.isNotNull(modelType) && modelType.equals(AuthServiceConstant.AUTH_MODEL_TYPE)) {
            view.setViewName("user/user_add_edit");
        } else {
            view.setViewName("user/user_view");
        }
        return view;
    }

    /**
     * 保存用户信息
     * @param userId 用户编码
     * @param userSaveQuery 用户数据入参
     * @param modelType 用户操作类型，用于区别重定向url
     * @return 保存用户信息
     */
    @PostMapping(value = "/save")
    public ModelAndView saveUser(String userId, UserSaveQuery userSaveQuery, String modelType) {
        ModelAndView view = new ModelAndView();
        if (userId != null) {
            userService.update(userId, userSaveQuery);
        } else {
            userService.insert(userSaveQuery);
        }
        if (StringUtils.isNotNull(modelType) && modelType.equals(AuthServiceConstant.SAVE_INFO_TYPE)) {
            view.setViewName("redirect:info?loginName=" + userSaveQuery.getLoginName());
        } else {
            view.setViewName("redirect:manager");
        }
        return view;
    }
    /**
     * 用户信息
     * @param model model
     * @param loginName 用户登录名
     * @return 显示用户信息
     */
    @GetMapping(value = "/info")
    public String userInfo(Model model, String loginName) {
        UserDO userDO = userService.getByUsername(loginName);
        model.addAttribute("userInfo", userDO);
        return "user/user_info";
    }

    /**
     * 更新用户密码
     * @param userUpdatePassQuery 更新用户密码入参类
     * @return 是否成功
     */
    @PostMapping(value = "/resetPassword")
    public ModelAndView updatePassword(UserUpdatePassQuery userUpdatePassQuery) {
        ModelAndView view = new ModelAndView();
        userService.updatePasswordByLoginName(userUpdatePassQuery);
        view.setViewName("redirect:/logout");
        return view;
    }

    /**
     * 切换用户状态
     * @param loginName 用户登录名
     * @param status 状态
     * @return 返回前端页面
     */
    @GetMapping(value = "/switchStatus")
    public ModelAndView switchStatus(String loginName, Boolean status) {
        ModelAndView view = new ModelAndView();
        userService.updateStatusByLoginName(loginName, status);
        view.setViewName("redirect:manager");
        return view;
    }
}
