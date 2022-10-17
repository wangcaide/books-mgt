package com.github.wangcaide.common.exception;

import com.github.wangcaide.common.enums.ErrorCode;
import com.github.wangcaide.common.model.R;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * 统一异常处理
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-16 10:46:21
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R defaultErrorHandler(HttpServletRequest request, Exception exception) throws Exception {
        if (exception instanceof BindException){
            return R.error(
                    ErrorCode.CLIENT_ERROR.getCode(),
                    "参数错误 - " + Objects.requireNonNull(((BindException) exception).getFieldError()).getDefaultMessage());
        }else {
            return R.error(exception.getMessage());
        }
    }

}
