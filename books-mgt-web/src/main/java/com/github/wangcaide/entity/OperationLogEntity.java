package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity OperationLog 操作日志
 * @author : wangcaide@outlook.com
 * @date : 2022-10-17
 */
@TableName("OPERATION_LOG")
@Data
public class OperationLogEntity extends BaseEntity {

    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    /** 操作内容 */
    private String description ;
    /** 执行状态 */
    private String status ;

}