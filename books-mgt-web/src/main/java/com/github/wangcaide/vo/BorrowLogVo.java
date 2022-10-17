package com.github.wangcaide.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * <p>
 * 借阅记录vo
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 15:27:28
 */
@Data
@Builder
public class BorrowLogVo implements Serializable {

    @Tolerate
    public BorrowLogVo() {
    }

    private String bookIsbn;
    private String bookName;
    private String borrowBy;

}
