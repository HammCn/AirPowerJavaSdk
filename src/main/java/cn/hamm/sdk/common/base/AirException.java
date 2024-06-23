package cn.hamm.sdk.common.base;

/**
 * <h1>异常</h1>
 *
 * @author Hamm.cn
 */
public class AirException extends RuntimeException {
    /**
     * <h2>状态码</h2>
     */
    private int code = AirConstant.ERROR_CODE;

    /**
     * <h2>获取状态码</h2>
     *
     * @return 状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * <h2>设置状态码</h2>
     *
     * @param code 状态码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * <h2>异常</h2>
     *
     * @param code    状态码
     * @param message 错误信息
     */
    public AirException(int code, String message) {
        super(message);
    }
}
