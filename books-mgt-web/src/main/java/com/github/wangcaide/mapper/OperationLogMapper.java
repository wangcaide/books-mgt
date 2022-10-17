package com.github.wangcaide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.wangcaide.entity.OperationLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志;(OPERATION_LOG)表数据库访问层
 * @date : 2022-10-17
 */
@Mapper
public interface OperationLogMapper  extends BaseMapper<OperationLogEntity>{

}