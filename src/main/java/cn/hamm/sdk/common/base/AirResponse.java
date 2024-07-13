package cn.hamm.sdk.common.base;

/**
 * <h1>响应基模型</h1>
 *
 * @author Hamm.cn
 */
public class AirResponse<R extends AirResponse<R>> {
    /**
     * <h2>错误代码</h2>
     */
    private int code;

    /**
     * <h2>错误信息</h2>
     */
    private String message;

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
    public AirResponse<R> setCode(int code) {
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
    public AirResponse<R> setMessage(String message) {
        this.message = message;
        return this;
    }
}
