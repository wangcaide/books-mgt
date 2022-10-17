package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.ResourceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 资源(系统/菜单/按键/链接);(RESOURCE)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface ResourceMapper extends BaseMapper<ResourceEntity>{

    @Select("select DISTINCT r2.* from ACCOUNT a " +
            "left join ACCOUNT_ROLE_RELATION arr on a.ACCOUNT_ID = arr.ACCOUNT_ID " +
            "left join `ROLE` r on arr.ROLE_ID = r.ROLE_ID " +
            "left join ROLE_RESOURCE_RELATION rrr on r.ROLE_ID = rrr.ROLE_ID " +
            "left join RESOURCE r2 on rrr.RESOURCE_ID = r2.RESOURCE_ID " +
            "where a.USERNAME = #{username} and a.STATUS = 'valid' " +
            "and arr.STATUS = 'valid' and r.STATUS = 'valid' and rrr.STATUS = 'valid' " +
            "and r2.STATUS = 'valid' and r2.resource_url is not null ")
    List<ResourceEntity> selectByUsername(@Param("username") String username);

}