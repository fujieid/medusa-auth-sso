/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: UserServiceImpl
 * Author:   Allen
 * Date:     2020/12/15
 * Description: 用户登陆Service层处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.chery.auth.client.constant.AuthEnumConstant;
import com.chery.auth.client.query.UserSaveQuery;
import com.chery.auth.client.query.UserUpdatePassQuery;
import com.chery.auth.core.dao.UserDAO;
import com.chery.auth.core.entity.UserDO;
import com.chery.auth.core.enums.SexTypeEnum;
import com.chery.common.exception.ServiceException;
import com.chery.common.util.SimpleConverter;
import com.chery.common.util.converter.CustomEnumConverter;
import com.fujieid.jap.ids.model.UserInfo;
import com.fujieid.jap.ids.service.IdsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.chery.auth.client.constant.AuthEnumConstant.USER_BAN;

/**
 * 〈用户Service层处理〉<br>
 * 〈用户Service实现类〉
 *
 * @author Allen
 * @since 1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDAO, UserDO> implements IdsUserService {
    /**
     * Login with account and password
     *
     * @param username account number
     * @param password password
     * @return UserInfo
     */
    @Override
    public UserInfo loginByUsernameAndPassword(String username, String password, String clientId) {
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.nested(wq -> wq.eq("login_name", username).or().eq("phone", username))
                .eq("status", true);
        return SimpleConverter.convert(super.getOne(userQueryWrapper), UserInfo.class);
    }

    /**
     * 根据username查找用户信息(支持登录名与手机号)
     *
     * @param username 用户登录名称
     * @return 用户信息
     */
    @Override
    @Cacheable(value = "getByName", key = "'userName:' + #username")
    public UserInfo getByName(String username, String clientId) {
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.nested(wq -> wq.eq("login_name", username).or().eq("phone", username))
                .eq("status", true);
        return SimpleConverter.convert(super.getOne(userQueryWrapper), UserInfo.class);
    }

    /**
     * 根据username查找用户信息(支持登录名与手机号)
     *
     * @param username 用户登录名称
     * @return 用户信息
     */
    @Cacheable(value = "getAuthUser", key = "'userName:' + #username")
    public UserDO getByUsername(String username) {
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.nested(wq -> wq.eq("login_name", username).or().eq("phone", username))
                .eq("status", true);
        return super.getOne(userQueryWrapper);
    }

    /**
     * 检测手机号是否存在
     *
     * @param phone     手机号
     * @param loginName 登录名
     * @return 是否存在（true，存在，false 不存在）
     */
    public Boolean checkPhoneUnique(String phone, String loginName) {
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone).ne("login_name", loginName);
        Boolean phoneUnique = SqlHelper.retBool(count(userQueryWrapper));
        if (phoneUnique) {
            throw new ServiceException(AuthEnumConstant.PHONE_EXITS_ERROR, phone);
        }
        return false;
    }

    /**
     * 得到所有用户列表
     *
     * @return 全部用户列表
     */
    @Cacheable(value = "authUserList", key = "'all'")
    public List<UserDO> listAll() {
        return super.list();
    }

    /**
     * 更新用户密码
     *
     * @param userUpdatePassQuery 用户密码信息入参
     * @return 是否成功
     */
    public Boolean updatePasswordByLoginName(@Validated UserUpdatePassQuery userUpdatePassQuery) {
        if (userUpdatePassQuery.getOldPassword().equals(userUpdatePassQuery.getNewPassword())) {
            throw new ServiceException(AuthEnumConstant.PASSWORD_CONSISTENT_ERROR);
        }
        if (!userUpdatePassQuery.getNewPassword().equals(userUpdatePassQuery.getVerifyNewPassword())) {
            throw new ServiceException(AuthEnumConstant.VERIFY_PASSWORD_ERROR);
        }
        UserDO userDO = getByUsername(userUpdatePassQuery.getLoginName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean checkPass = passwordEncoder.matches(userUpdatePassQuery.getOldPassword(),
                userDO.getLoginPassword());
        if (checkPass) {
            return resetUserPwd(userUpdatePassQuery.getLoginName(),
                    userUpdatePassQuery.getNewPassword());
        }
        throw new ServiceException(AuthEnumConstant.PASSWORD_CHECK_ERROR);
    }

    /**
     * 更新用户密码
     *
     * @param loginName 用户登录名
     * @param password  用户新密码
     * @return 是否更新成功
     */
    private Boolean resetUserPwd(String loginName, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UpdateWrapper<UserDO> resetPwdWrapper = new UpdateWrapper();
        resetPwdWrapper.set("login_password", passwordEncoder.encode(password)).eq("login_name", loginName);
        return super.update(resetPwdWrapper);
    }

    /**
     * 保存用户信息
     *
     * @param userSaveQuery 保存用户信息
     * @return 保存成功标识
     */
    @CacheEvict(value = "authUserList", key = "'all'")
    public Boolean insert(UserSaveQuery userSaveQuery) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userSaveQuery, userDO);
        return super.save(userDO);
    }

    /**
     * 更新用户信息
     *
     * @param userId        用户编码
     * @param userSaveQuery 用户数据信息
     * @return 更新后的用户信息
     */
    @Caching(put = {@CachePut(value = "getAuthUser", key = "'userName:' + #userSaveQuery.loginName"),
            @CachePut(value = "authUser", key = "#userId")},
            evict = @CacheEvict(value = "authUserList", key = "'all'"))
    public UserDO update(String userId, UserSaveQuery userSaveQuery) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        UserDO userDO = super.getOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(USER_BAN, userId);
        }
        checkPhoneUnique(userSaveQuery.getPhone(), userSaveQuery.getLoginName());
        BeanUtils.copyProperties(userSaveQuery, userDO);
        userDO.setUserId(userId);
        userDO.setSex(CustomEnumConverter.getEnumConverter(SexTypeEnum.class, userSaveQuery.getSex().toString()));
        super.updateById(userDO);
        return userDO;
    }

    /**
     * 得到用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    @Override
    @Cacheable(value = "authUser", key = "'userId:' + #userId")
    public UserInfo getById(String userId) {
        return SimpleConverter.convert(super.getById(userId), UserInfo.class);
    }

    /**
     * 切换用户状态
     *
     * @param loginName 用户登录名
     * @param status    状态
     * @return 更新标识
     */
    @CachePut(value = "getAuthUser", key = "'userName:' + #loginName")
    public Boolean updateStatusByLoginName(String loginName, Boolean status) {
        UserDO userDO = getByUsername(loginName);
        userDO.setStatus(status);
        return super.updateById(userDO);
    }
}



