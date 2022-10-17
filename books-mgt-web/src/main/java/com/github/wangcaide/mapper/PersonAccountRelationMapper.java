package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.PersonAccountRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 个人信息账户关系表;(PERSON_ACCOUNT_RELATION)表数据库访问层
 * @date : 2022-10-16
 */
@Mapper
public interface PersonAccountRelationMapper extends BaseMapper<PersonAccountRelationEntity>{

}