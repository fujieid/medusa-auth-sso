/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: AuthEnumConstant
 * Author:   Allen
 * Date:     2020/12/15
 * Description: AuthServer枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.client.constant;


import com.chery.common.core.enums.ICodeEnum;

/**
 * auth server rest接口编码，AuthCodeEnum包含编码为auth server服务级错误编码，编码范围从4008~4040
 * @author Allen
 * @date 2020-12-10
 */
public enum AuthEnumConstant implements ICodeEnum {
    /**
     * 用户不存在或禁止登录
     */
    USER_BAN(false, "4008", "用户名为{}的用户不存在或已被禁用！"),
    /**
     * 刷新API错误提示。
     */
    REFRESH_API_ERROR(false, "4009", "刷新API异常，请重试或联系管理员！"),
    /**
     * 新密码和旧密码不能一样
     */
    PASSWORD_CONSISTENT_ERROR(false, "4010", "新密码和旧密码不能一样"),
    /**
     * 新密码和旧密码不能一样
     */
    PASSWORD_CHECK_ERROR(false, "4011", "旧密码不正确，请重新输入！"),
    /**
     * 未找到对应的客户端编码！
     */
    CLIENT_NOT_ERROR(false, "4012", "客户端编码为{}的不存在，请重新选择！"),
    /**
     * 两次新密码不一致！
     */
    VERIFY_PASSWORD_ERROR(false, "4013", "两次新密码不一致，请返回重新输入！"),
    /**
     * 联系电话已存在
     */
    PHONE_EXITS_ERROR(false, "4057", "{}联系电话已存在"),;

    /**
     * 构造方法
     */
    /**
     * 成功标示
     */
    private Boolean success;
    /**
     * 响应代码
     */
    private String code;
    /**
     * 响应代码说明
     */
    private String message;

    /**
     * 构造方法
     */
    AuthEnumConstant(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 结果
     *
     * @return 操作结果
     */
    @Override
    public Boolean success() {
        return this.success;
    }

    /**
     * 编号
     *
     * @return 操作结果编号
     */
    @Override
    public String code() {
        return this.code;
    }

    /**
     * 说明
     *
     * @return 操作结果说明
     */
    @Override
    public String message() {
        return this.message;
    }
}
