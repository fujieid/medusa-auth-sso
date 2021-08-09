 /*
  * Copyright (C), 2021, 安徽贝慕信息科技有限公司
  * FileName: SexType
  * Author:   Allen
  * Date:     2021/1/7 17:30
  * Description:
  * History:
  * <author>          <time>          <version>          <desc>
  * 作者姓名           修改时间           版本号              描述
  */
 package com.chery.auth.core.enums;

 import com.alibaba.fastjson.annotation.JSONType;
 import com.alibaba.fastjson.serializer.SerializerFeature;
 import com.fasterxml.jackson.annotation.JsonFormat;
 import com.chery.common.core.enums.IBaseEnum;

 /**
  * 〈一句话功能简述〉<br>
  * 〈性别枚举〉
  *  <p>
  *      这里的@JSONType注解可以不用特别加，默认即是
  *      serializeEnumAsJavaBean = true此方法不可增加，会造成无法序列化问题
  *  </p>
  * @author Allen
  * @date 2021/1/7
  * @since 1.0.0
  */
 @JsonFormat(shape = JsonFormat.Shape.OBJECT)
 @JSONType(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
 public enum SexTypeEnum implements IBaseEnum<Integer> {
     /**
      * 男
      */
     MAN(1, "男"),
     /**
      * 女
      */
     WOMAN(2, "女"),

     /**
      * 未知
      */
     UNKNOWN(0, "未知");

     /**
      * 值
      */
     Integer value;
     /**
      * 描述信息
      */
     String desc;


     /**
      * 架造器
      *
      * @param value 值
      * @param desc  描述信息
      */
     SexTypeEnum(Integer value, String desc) {
         this.value = value;
         this.desc = desc;
     }

     @Override
     public String getDesc() {
         return desc;
     }

     @Override
     public String getName() {
         return this.name();
     }

     @Override
     public Integer getValue() {
         return value;
     }
 }
