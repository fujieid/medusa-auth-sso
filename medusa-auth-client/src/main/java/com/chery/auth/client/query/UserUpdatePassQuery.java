/*
 * Copyright (C), 2021, 安徽艾伦家居饰品有限公司
 * FileName: UserUpdatePassQuery
 * Author:   Allen
 * Date:     2021/1/2
 * Description: 用户更新密码的Query消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈用户更新密码的Query消息〉
 *
 * @author Allen
 * @date 2020/9/2
 * @since 1.0.0
 */
@Data
public class UserUpdatePassQuery implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(name = "loginName", value = "用户登录名", example = "Allen")
    String loginName;

    /**
     * 旧密码
     */
    @ApiModelProperty(name = "oldPassword", value = "旧密码", example = "123456")
    String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(name = "newPassword", value = "新密码", example = "123456")
    String newPassword;

    /**
     * 确认新密码
     */
    @ApiModelProperty(name = "verifyNewPassword", value = "确认新密码", example = "123456")
    String verifyNewPassword;
}
