package cn.hamm.sdk.common.base;

/**
 * <h1>AbstractResponse</h1>
 *
 * @param <R> 响应模型
 * @author Hamm.cn
 */
public abstract class AbstractResponse<R extends AbstractResponse<R>> {
    /**
     * <h2>解析数据</h2>
     *
     * @param data 解密后的data数据
     * @return 解析后的数据
     */
    public abstract R parseData(String data);
}
