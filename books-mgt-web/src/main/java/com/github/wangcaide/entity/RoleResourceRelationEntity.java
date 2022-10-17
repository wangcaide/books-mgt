package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity RoleResourceRelation 角色资源关系
 * @author : wangcaide@outlook.com
 * @date : 2022-10-17
 */
@TableName("ROLE_RESOURCE_RELATION")
@Data
public class RoleResourceRelationEntity extends BaseEntity {
    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    /** 角色id */
    private Integer roleId ;
    /** 资源id */
    private Integer resourceId ;
    /** 状态 */
    private String status ;

}
