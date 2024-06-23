package cn.hamm.sdk.common.base;

/**
 * <h1>AirPower标准JSON格式</h1>
 *
 * @param <R> 响应模型
 * @author Hamm.cn
 */
public class AirJson<R extends AirJson<R>> {
    /**
     * <h2>错误代码</h2>
     */
    private int code;

    /**
     * <h2>错误信息</h2>
     */
    private String message;

    /**
     * <h2>响应数据</h2>
     */
    private String data;

    /**
     * <h2>获取错误代码</h2>
     *
     * @return 错误代码
     */
    public int getCode() {
        return code;
    }

    /**
     * <h2>设置错误代码</h2>
     *
     * @param code 错误代码
     * @return AirPowerResponse
     */
    public AirJson<R> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * <h2>获取错误信息</h2>
     *
     * @return 错误信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * <h2>设置错误信息</h2>
     *
     * @param message 错误信息
     * @return AirPowerResponse
     */
    public AirJson<R> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * <h2>获取响应数据</h2>
     *
     * @return 响应数据
     */
    public String getData() {
        return data;
    }

    /**
     * <h2>设置响应数据</h2>
     *
     * @param data 响应数据
     * @return AirPowerResponse
     */
    public AirJson<R> setData(String data) {
        this.data = data;
        return this;
    }
}
