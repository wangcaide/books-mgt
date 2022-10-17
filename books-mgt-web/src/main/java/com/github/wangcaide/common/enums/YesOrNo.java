package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * yes or no
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 11:01:29
 */
@AllArgsConstructor
@Getter
public enum YesOrNo {

    YES("Y", "yes"),
    NO("N", "not");

    private String dbValue;
    private String description;

}
