/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientService
 * Author:   Allen
 * Date:     2020/8/15
 * Description: Client服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chery.auth.client.query.ClientQuery;
import com.chery.auth.core.dao.ClientDAO;
import com.chery.auth.core.entity.ClientDO;
import com.chery.common.core.model.query.PageQuery;
import com.chery.common.exception.ServiceException;
import com.chery.common.util.SimpleConverter;
import com.fujieid.jap.ids.model.ClientDetail;
import com.fujieid.jap.ids.service.IdsClientDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.chery.auth.client.constant.AuthEnumConstant.CLIENT_NOT_ERROR;

/**
 * 客户端服务
 *
 * @author Allen
 * @date 2020/8/18
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDAO, ClientDO> implements IdsClientDetailService {
    /**
     * 新增客户端信息
     *
     * @param clientQuery 客户端信息
     * @return 插入记录
     */
    @CacheEvict(value = "authClientList", key = "'all'")
    public Boolean insert(ClientQuery clientQuery) {
        ClientDO clientDO = new ClientDO();
        BeanUtils.copyProperties(clientQuery, clientDO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bcryPassword = bCryptPasswordEncoder.encode(clientQuery.getClientSecret() == null
                ? clientQuery.getClientId() : clientDO.getClientSecret());
        clientDO.setClientSecret(bcryPassword);
        return save(clientDO);
    }

    /**
     * 更新客户端信息
     *
     * @param id          主键
     * @param clientQuery 客户端信息
     * @return 更新记录
     */
    @Caching(put = @CachePut(value = "client", key = "#clientQuery.clientId"),
            evict = @CacheEvict(value = "authClientList", key = "'all'"))
    public ClientDO update(String id, ClientQuery clientQuery) {
        QueryWrapper<ClientDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientQuery.getClientId());
        ClientDO clientDO = getOne(queryWrapper);
        if (clientDO == null) {
            throw new ServiceException(CLIENT_NOT_ERROR, clientQuery.getClientId());
        }
        BeanUtils.copyProperties(clientQuery, clientDO);
        clientDO.setId(id);
        if (!StringUtils.isEmpty(clientQuery.getClientSecret())) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String bcryPassword = bCryptPasswordEncoder.encode(clientQuery.getClientSecret());
            clientDO.setClientSecret(bcryPassword);
        }
        updateById(clientDO);
        return clientDO;
    }

    /**
     * 分页查询
     *
     * @param clientQuery 查询参数
     * @param pageQuery   分页参数
     * @return Page<ClientDO> 分页数据
     */
    public IPage<ClientDO> listByPage(ClientQuery clientQuery, PageQuery pageQuery) {
        ClientDO clientDO = new ClientDO();
        BeanUtils.copyProperties(clientQuery, clientDO);
        Wrapper<ClientDO> wrapper = new QueryWrapper(clientDO);
        IPage<ClientDO> page = page(new Page<>(pageQuery.getPageIndex(),
                pageQuery.getPageSize()), wrapper);
        return page;
    }

    /**
     * 根据clientId删除
     *
     * @param id id
     * @return 删除是否成功
     */
    @Override
    @Caching(evict = {@CacheEvict(value = "authClientList", key = "'all'")})
    public boolean removeById(String id) {
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.eq(true, "id", id);
        return remove(qwrapper);
    }

    /**
     * 根据clientId删除
     *
     * @param clientId clientId
     * @return 删除是否成功
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value = "client", key = "#clientId"),
            @CacheEvict(value = "authClientList", key = "'all'")})
    public boolean removeByClientId(String clientId) {
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.eq(true, "client_id", clientId);
        return remove(qwrapper);
    }

    /**
     * 根据clientId获取详情
     *
     * @param clientId clientId
     * @return 客户端DO信息
     */
    @Override
    @Cacheable(value = "client", key = "#clientId", condition = "#result != null ")
    public ClientDetail getByClientId(String clientId) {
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.eq(true, "client_id", clientId);
        return  SimpleConverter.convert(getOne(qwrapper), ClientDetail.class);
    }

    /**
     * 得到所有客户端列表
     *
     * @return 所有客户端列表
     */
    @Override
    @Cacheable(value = "authClientList", key = "'all'")
    public List<ClientDetail> getAllClientDetail() {
        return SimpleConverter.convert(this.list(), ClientDetail.class);
    }
}
