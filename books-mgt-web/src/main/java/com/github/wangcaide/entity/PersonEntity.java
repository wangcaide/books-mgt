package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.wangcaide.entity.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity Person 个人信息
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("PERSON")
@Data
public class PersonEntity extends BaseEntity {
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer personId ;
    /** 名字 */
    private String firstName ;
    /** 姓 */
    private String lastName ;
    /** 证件类型 */
    private String idType ;
    /** 证件号 */
    private String idValue ;
    /** 状态 */
    private String status ;

}