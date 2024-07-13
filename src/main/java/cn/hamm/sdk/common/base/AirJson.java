package cn.hamm.sdk.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <h1>AirPower Json</h1>
 *
 * @param <R> 响应模型
 * @author Hamm.cn
 */
public class AirJson<R extends AirJson<R>> {
    /**
     * <h2>{@code ObjectMapper}</h2>
     */
    private static ObjectMapper objectMapper;

    /**
     * <h2>{@code Json} 反序列化到指定类</h2>
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
     * <h2>{@code Json} 反序列化为 {@code Map}</h2>
     *
     * @param json 字符串
     * @return {@code Map}
     */
    public static Map<String, Object> parse2Map(String json) {
        try {
            TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
            };
            return getObjectMapper().readValue(json, typeRef);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    /**
     * <h2>{@code Json} 反序列化为 {@code ListMap}</h2>
     *
     * @param json 字符串
     * @return {@code List<Map>}
     */
    public static List<Map<String, Object>> parse2MapList(String json) {
        try {
            TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<List<Map<String, Object>>>() {
            };
            return getObjectMapper().readValue(json, typeRef);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * <h2>{@code Json} 序列化到字符串</h2>
     *
     * @param object 对象
     * @return 字符串
     */
    public static String toString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * <h2>获取一个配置后的 {@code ObjectMapper}</h2>
     *
     * @return {@code ObjectMapper}
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
