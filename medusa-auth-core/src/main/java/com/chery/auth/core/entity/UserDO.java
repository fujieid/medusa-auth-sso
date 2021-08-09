/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: UserDO
 * Author:   Allen
 * Date:     2020/12/15
 * Description: 用户登陆表DO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chery.auth.core.enums.SexTypeEnum;
import com.chery.auth.core.enums.UserTypeEnum;
import com.chery.common.core.entity.BaseNoOperatorDO;
import lombok.Data;

/**
 * 〈用户登陆表DO,此处需要缓存的 Java 对象必须实现 Serializable 接口〉
 *
 * @author Allen
 * @date 2020-12-21
 * @since 1.0.0
 */

@Data
@TableName(value = "oauth_sys_user")
public class UserDO extends BaseNoOperatorDO {

    /**
     * 用户编码(同userId)
     */
    @TableField(value = "user_id")
    private String id;

    /**
     * 用户编码
     */
    @TableId
    private String userId;

    /**
     * 用户名称(同userName)
     */
    @TableField(value = "user_name")
    private String username;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别（"1=男,2=女,0=未知"）
     */
    private SexTypeEnum sex;
}
