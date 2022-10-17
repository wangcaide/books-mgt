package com.github.wangcaide.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 错误码枚举
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 09:49:47
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {
    SUCCESS("200", "成功"),
    CLIENT_ERROR("400", "客户端错误"),
    NOT_FOUND("404", "未找到"),
    INTERNAL_SERVER_ERROR("500", "内部服务器错误"),
    ;
    private String code;
    private String message;
}
