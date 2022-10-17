package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * OperationLog 状态
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 14:10:44
 */
@AllArgsConstructor
@Getter
public enum OperationLogStatus {
    SUCCESS("success", "成功"),
    FAILURE("FAILURE", "失败")
    ;

    private String dbValue;
    private String description;
}
