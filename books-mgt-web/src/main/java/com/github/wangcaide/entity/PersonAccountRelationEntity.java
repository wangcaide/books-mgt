package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.wangcaide.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.experimental.Tolerate;

/**
 * Entity PersonAccountRelation 个人信息账户关系表
 * @author : wangcaide@outlook.com
 * @date : 2022-10-16
 */
@TableName("PERSON_ACCOUNT_RELATION")
@Data
@Builder
public class PersonAccountRelationEntity extends BaseEntity {
    @Tolerate
    public PersonAccountRelationEntity() {
    }
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    /** 个人信息表ID */
    private Integer personId ;
    /** 账户id */
    private Integer accountId ;
    /** 状态 */
    private String status ;

}