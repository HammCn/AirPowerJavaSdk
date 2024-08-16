package cn.hamm.sdk.common.base;

import java.lang.reflect.ParameterizedType;

/**
 * <h1>AbstractRequest</h1>
 *
 * @param <R> 响应模型
 * @author Hamm.cn
 */
public abstract class AbstractRequest<R extends AbstractResponse<R>> {
    /**
     * <h2>API地址</h2>
     *
     * @return API地址
     */
    protected abstract String getApiUrl();

    /**
     * <h2>获取响应类</h2>
     *
     * @return 类
     */
    Class<R> getResponseClass() {
        //noinspection unchecked
        return (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
