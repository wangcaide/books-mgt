package com.github.wangcaide.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity AccountRoleRelation 账户角色关系
 * @author : wangcaide@outlook.com
 * @date : 2022-10-17
 */
@TableName("ACCOUNT_ROLE_RELATION")
@Data
public class AccountRoleRelationEntity extends BaseEntity {
    /** ID */
    @TableId
    private Integer id ;
    /** 账户ID */
    private Integer accountId ;
    /** 角色ID */
    private Integer roleId ;
    /** 状态 */
    private String status ;

}