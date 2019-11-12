package com.shine.api.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类.
 *
 * @author shineYu
 * @Date 2019/6/12 11:01
 */
public class JsonUtils {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    /**
     * 对象转json字符串.
     *
     * @param value 对象
     * @return    json字符串
     * @throws Exception
     */
    public static String objectToJsonStr(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    /**
     * json字符串转对象.
     *
     * @param content  json字符串
     * @param valueType  对象类型
     * @return   对象
     * @throws Exception 异常
     */
    public static <T> T objectFromJsonStr(String content,Class<T> valueType) throws Exception {
        T obj = objectMapper.readValue(content, valueType);
        return obj;
    };
}
