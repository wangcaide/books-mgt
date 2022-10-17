package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.wangcaide.entity.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity Role 账户角色信息
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("ROLE")
@Data
public class RoleEntity extends BaseEntity {
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer roleId ;
    /** 角色码 */
    private String roleCode ;
    /** 角色名称 */
    private String roleName ;
    /** 状态 */
    private String status ;

}