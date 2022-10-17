package com.github.wangcaide.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 证件类型枚举
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 09:20:55
 */
@AllArgsConstructor
@Getter
public enum IdType {

    IDENTITY_CARD("identity_card", "身份证");

    private String dbValue;
    private String description;

}
