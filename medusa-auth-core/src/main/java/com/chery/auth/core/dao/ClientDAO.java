/*
 * Copyright (C), 2021, Anhui Allen Home Accessories Co., Ltd
 * FileName: ClientDAO
 * Author:   Allen
 * Date:     2020/8/15
 * Description: ClientDO实体数据操作对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.chery.auth.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chery.auth.core.entity.ClientDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClientDO实体数据操作对象
 *
 * @author Allen
 * @date 2020-07-10
 */
@Mapper
public interface ClientDAO extends BaseMapper<ClientDO> {
}
