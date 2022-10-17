package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.ContactEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 联系方式;(Contact)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface ContactMapper extends BaseMapper<ContactEntity>{

}