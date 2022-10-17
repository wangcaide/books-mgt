package com.github.wangcaide.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * <p>
 * json工具类
 * </p>
 * @author Caide, Wang (wangcaide@outlook.com)
 * @version v1.0.0
 * @date 2022-10-17 14:21:22
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static String toJSONString(Object obj) {
        if (Objects.isNull(obj)) {
            return StringUtils.EMPTY;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return RegExUtils.replaceAll(objectMapper.writeValueAsString(obj), "\"", "\'");
        } catch (JsonProcessingException e) {
            log.error("to jsonString exception for class {}", obj.getClass(), e);
        }
        return StringUtils.EMPTY;
    }

}
