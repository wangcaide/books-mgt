package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * Entity BorrowLog 借阅记录
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("BORROW_LOG")
@Data
public class BorrowLogEntity extends BaseEntity {
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer id ;
    /** 图书ISBN码 */
    private String bookIsbn ;
    /** 图书名称 */
    private String bookName ;
    /** 借阅人 */
    private String borrowBy ;
    /** 借阅时间 */
    private Date borrowTime ;
    /** 到期时间 */
    private Date expirationTime ;
    /** 归还时间 */
    private Date returnTime ;

}