package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.BorrowLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 借阅记录;(BORROW_LOG)表数据库访问层
 * @date : 2022-10-15
 */
@Mapper
public interface BorrowLogMapper  extends BaseMapper<BorrowLogEntity>{

    @Select("select * from BORROW_LOG where ID = #{id} for update")
    public BorrowLogEntity lockById(@Param("id") int id);

}