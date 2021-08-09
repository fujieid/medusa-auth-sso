/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: UserDAO
 * Author:   Allen
 * Date:     2020/8/15
 * Description: 实现用户登陆表的业务处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chery.auth.core.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 〈实现用户登陆表的业务处理〉<br>
 * 〈用户登陆DAO〉
 *
 * @author Allen
 * @date 2020/12/21
 * @since 1.0.0
 */
@Mapper
public interface UserDAO extends BaseMapper<UserDO> {
}


