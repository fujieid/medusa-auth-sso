/*
 * Copyright (C), 2021, 安徽艾伦家居饰品有限公司
 * FileName: UserSaveQuery
 * Author:   Allen
 * Date:     2021/2/6
 * Description: 用户信息保存对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 〈用户信息保存对象,此处需要缓存的 Java 对象必须实现 Serializable 接口〉
 *
 * @author Allen
 * @date 2020-12-21
 * @since 1.0.0
 */

@Data
public class UserSaveQuery implements Serializable {

    /**
     * 用户名称
     */
    @ApiParam(value = "用户名称", example = "艾伦")
    private String userName;

    /**
     * 登录名称
     */
    @ApiParam(value = "登录名称", example = "Allen")
    private String loginName;

    /**
     * 手机号码
     */
    @ApiParam(value = "手机号码", example = "13696560594")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiParam(value = "电子邮箱", example = "apicescn@hotmail.com")
    private String email;
    /**
     * 用户状态
     */
    @ApiParam(value = "用户状态", example = "true")
    private Boolean status;

    /**
     * 性别
     */
    @ApiParam(value = "性别", example = "1")
    private Integer sex;
}
