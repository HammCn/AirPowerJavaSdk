package cn.hamm.sdk.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Objects;

/**
 * <h1>Json对象处理</h1>
 *
 * @author Hamm.cn
 */
public class Json {

    private static ObjectMapper objectMapper;

    private Json() {
        // 禁止外部实例化
    }

    /**
     * <h2>Json反序列化到指定类</h2>
     *
     * @param json  字符串
     * @param clazz 目标类
     * @param <E>   目标类
     * @return 目标类的实例
     */
    public static <E> E parse(String json, Class<E> clazz) {
        try {
            return getObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * <h2>Json序列化到字符串</h2>
     *
     * @param object 对象
     * @return 字符串
     */
    public static String toString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>获取一个配置后的ObjectMapper</h2>
     *
     * @return ObjectMapper
     */
    private static ObjectMapper getObjectMapper() {
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
            // 忽略未声明的属性
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 忽略值为null的属性
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 忽略没有属性的类
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
        return objectMapper;
    }
}