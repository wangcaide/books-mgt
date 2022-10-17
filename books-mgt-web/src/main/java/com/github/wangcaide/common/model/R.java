package com.github.wangcaide.common.model;

import com.github.wangcaide.common.enums.ErrorCode;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

import java.io.Serializable;

/**
 * <p>
 * 通用接口响应对象
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 10:26:22
 */
@Data
@Builder
@Slf4j
public class R<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public static R error(String errCode, String msg, String... param) {
        return R.builder().code(errCode)
                .message(MessageFormatter.format(msg, param).getMessage())
                .build();
    }

    public static R error(ErrorCode code, String... param) {
        return R.builder().code(code.getCode())
                .message(MessageFormatter.format(code.getMessage(), param).getMessage())
                .build();
    }
    public static R error(String msg) {
        return error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static R empty() {
        return error(ErrorCode.NOT_FOUND);
    }

    public static R ok() {
        return R.builder().code(ErrorCode.SUCCESS.getCode()).message(ErrorCode.SUCCESS.getMessage()).build();
    }

    public static <T> R ok(T data) {
        return R.builder().code(ErrorCode.SUCCESS.getCode()).message(ErrorCode.SUCCESS.getMessage()).data(data).build();
    }

}
