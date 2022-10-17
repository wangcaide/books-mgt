package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 联系方式类型
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 10:17:22
 */
@AllArgsConstructor
@Getter
public enum ContactType {

    PHONE_NUMBER("phone_number", "手机号"),
    EMAIL("email", "邮箱");

    private String dbCode;
    private String description;

}
