package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 账户;(ACCOUNT)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface AccountMapper  extends BaseMapper<AccountEntity>{

}