package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 状态枚举
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 09:20:49
 */
@AllArgsConstructor
@Getter
public enum CommonStatus {

    VALID("valid", "有效"),
    INVALID("invalid", "无效");

    private String dbValue;
    private String description;

}
