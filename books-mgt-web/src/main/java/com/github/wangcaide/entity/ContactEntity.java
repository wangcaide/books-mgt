package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.github.wangcaide.entity.BaseEntity;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity Contact 联系方式
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("CONTACT")
@Data
public class ContactEntity extends BaseEntity {
    /** id */
    @TableId(type = IdType.AUTO)
    private Integer contactId ;
    /** 联系方式(手机/邮箱/地址) */
    private String contactType ;
    /** 联系信息 */
    private String contactInfo ;
    /** 个人信息ID */
    private Integer personId ;
    /** 首选联系方式，Y/N */
    private String isPrimary ;
    /** 状态 */
    private String status ;

}