package com.github.wangcaide.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * <p>
 * BaseEntity
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 15:41:57
 */
@Getter
@Setter
public class BaseEntity {
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;

    public <T> T convertVO(T emptyVO) {
        BeanUtils.copyProperties(this, emptyVO);
        return emptyVO;
    }

}
