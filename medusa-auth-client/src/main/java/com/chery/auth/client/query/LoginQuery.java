/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: LoginQuery
 * Author:   Allen
 * Date:     2020年12月30日
 * Description: 用户登录参数
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户登录参数
 *
 * @author Allen
 * @date 2020/12/30
 */
@Data
public class LoginQuery {

    /**
     * 用户名称
     */
    @ApiModelProperty(name = "loginName", value = " 用户名称", required = true, example = "user")
    @NotNull
    @Size(min = 4, max = 20, message = "登录名长度范围为4-20")
    private String loginName;

    /**
         * 用户密码
     */
    @ApiModelProperty(name = "password", value = " 用户密码", required = true, example = "123456")
    @NotNull
    private String password;

}
