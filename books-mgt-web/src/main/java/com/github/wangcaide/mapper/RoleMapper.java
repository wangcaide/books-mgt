package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.common.constant.SysConstant;
import com.github.wangcaide.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;


/**
 * 角色;(ROLE)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity>{

    @Cacheable(cacheNames = SysConstant.DB_CACHE_NAME, key = "'reader_role_id'")
    @Select("SELECT ROLE_ID FROM ROLE WHERE ROLE_CODE = 'reader' AND STATUS = 'valid' ")
    public Integer findReaderRoleId();
}