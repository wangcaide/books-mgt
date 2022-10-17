package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.AccountRoleRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户角色关系表;(ACCOUNT_ROLE_RELATION)表数据库访问层
 * @date : 2022-10-16
 */
@Mapper
public interface AccountRoleRelationMapper extends BaseMapper<AccountRoleRelationEntity>{

}