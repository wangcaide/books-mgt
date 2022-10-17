package com.github.wangcaide.common.exception;

/**
 * <p>
 * 通用异常类
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-15 14:33:35
 */
public class WebException extends RuntimeException {
    public WebException(String message) {
        super(message);
    }

}
