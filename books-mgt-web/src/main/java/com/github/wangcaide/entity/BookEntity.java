package com.github.wangcaide.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Entity Book 图书
 * @author : wangcaide@outlook.com
 * @date : 2022-10-15
 */
@TableName("BOOK")
@Data
public class BookEntity extends BaseEntity {
    /** ID */
    @TableId(type = IdType.AUTO)
    private Integer bookId ;
    /** 图书ISBN码 */
    private String bookIsbn ;
    /** 图书名称 */
    private String bookName ;
    /** 出版社 */
    private String publisher ;
    /** 图书描述 */
    private String bookDescription ;
    /** 总库存 */
    private Integer totalNum ;
    /** 已借出数量 */
    private Integer borrowedNum ;

}