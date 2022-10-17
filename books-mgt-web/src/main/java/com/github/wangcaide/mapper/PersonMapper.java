package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.PersonEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 个人信息;(Person)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface PersonMapper extends BaseMapper<PersonEntity>{

}