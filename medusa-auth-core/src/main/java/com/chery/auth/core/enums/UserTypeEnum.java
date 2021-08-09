/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: UserTypeEnum
 * Author:   Allen
 * Date:     2020/12/15
 * Description: 用户类型枚举
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.chery.common.core.enums.IBaseEnum;

/**
 * 〈用户类型〉
 *
 * @author Allen
 * @date 2020/12/19
 * @since 1.0.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserTypeEnum implements IBaseEnum<Integer> {
    /**
     * 系统用户
     */
    SYSTEM(1, "系统用户"),
    /**
     * 普通用户
     */
    COMMUNITY(2, "普通用户"),

    /**
     * 其他用户
     */
    INSTALLER(3, "其他用户");

    /**
     * 值
     */
    Integer value = 0;
    /**
     * 描述信息
     */
    String desc = "";

    UserTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获得枚举值
     * @return 枚举值
     */
    @Override
    public Integer getValue() {
        return value;
    }

    /**
     * 获取描述
     *
     * @return 描述
     */
    @Override
    public String getDesc() {
        return desc;
    }

    /**
     * 得到名称
     *
     * @return 描述信息
     */
    @Override
    public String getName() {
        return this.name();
    }

}
