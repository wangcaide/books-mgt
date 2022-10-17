package com.github.wangcaide.common.log;

import com.github.wangcaide.common.enums.OperationLogStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 操作日志工具类
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 14:14:26
 */
public class OperationLogUtils {

    private static ThreadLocal<Map<String, Object>> params = new ThreadLocal<>();
    private static ThreadLocal<OperationLogStatus> status = new ThreadLocal<>();

    public static void addParam(String key, Object value) {
        if (params.get() == null) {
            params.set(new HashMap<>());
        }
        params.get().put(key, value);
    }

    public static void removeParams() {
        params.remove();
    }

    public static Map<String, Object> getParams() {
        return params.get() == null ? Collections.emptyMap() : params.get();
    }

    public static void removeStatus() {
        status.remove();
    }

    public static void success() {
        status.set(OperationLogStatus.SUCCESS);
    }

    public static void failure() {
        status.set(OperationLogStatus.FAILURE);
    }

    public static OperationLogStatus getStatus() {
        return status.get();
    }
    

}
