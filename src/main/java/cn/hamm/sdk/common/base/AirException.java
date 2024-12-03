package cn.hamm.sdk.common.base;

import cn.hamm.sdk.common.enums.AirErrorCode;

/**
 * <h1>AirException</h1>
 *
 * @author Hamm.cn
 */
public class AirException extends RuntimeException {
    /**
     * <h2>状态码</h2>
     */
    private int code = AirErrorCode.SERVICE_ERROR.getCode();

    /**
     * <h2>异常</h2>
     *
     * @param code    状态码
     * @param message 错误信息
     */
    public AirException(int code, String message) {
        super(message);
    }

    /**
     * <h2>异常</h2>
     *
     * @param message 错误信息
     */
    public AirException(String message) {
        super(message);
    }

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
}
