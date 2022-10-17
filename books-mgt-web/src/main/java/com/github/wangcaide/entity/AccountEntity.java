package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Builder;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.experimental.Tolerate;

/**
 * Entity Account 账户
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("ACCOUNT")
@Data
@Builder
public class AccountEntity extends BaseEntity {
    @Tolerate
    public AccountEntity() {
    }

    /** 账户ID */
    @TableId(type = IdType.AUTO)
    private Integer accountId ;
    /** 用户名(登陆名) */
    private String username ;
    /** 密码 */
    private String password ;
    /** 状态 */
    private String status ;

}