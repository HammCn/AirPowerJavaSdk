package cn.hamm.sdk.common.base;

import cn.hamm.sdk.common.enums.AirErrorCode;
import cn.hamm.sdk.common.util.*;

import java.util.Objects;

/**
 * <h1>AirPower Client</h1>
 *
 * @author Hamm.cn
 */
public class AirClient {
    /**
     * <h2>禁止外部实例化</h2>
     */
    private AirClient() {
    }

    /**
     * <h2>配置</h2>
     */
    private AirConfig config;

    /**
     * <h2>发起请求</h2>
     *
     * @param request 请求对象
     * @return 响应对象
     */
    public final <REQ extends AbstractRequest<RES>, RES extends AbstractResponse<RES>> RES request(REQ request) {
        AirRequest airRequest = new AirRequest()
                .setAppKey(config.getAppKey())
                .setContent(encrypt(request));
        airRequest.sign(config.getAppSecret());
        final String body = AirJson.toString(airRequest);
        String response = AirHttp.post(config.getGateway() + request.getApiUrl(), body);
        AirJson<?> airJson = AirJson.parse(response, AirJson.class);
        if (AirErrorCode.SUCCESS.getCode() != airJson.getCode()) {
            throw new AirException(airJson.getCode(), airJson.getMessage());
        }
        return decrypt(airJson.getData(), request.getResponseClass());
    }

    /**
     * <h2>解密</h2>
     *
     * @param content     加密后的数据
     * @param targetClass 目标类
     * @return 解密后的对象
     */
    public final <RES extends AbstractResponse<RES>> RES decrypt(String content, Class<RES> targetClass) {
        content = decrypt(content);
        return AirJson.parse(content, targetClass);
    }

    /**
     * <h2>解密</h2>
     *
     * @param content 加密后的数据
     * @return 解密后的字符串
     */
    public final String decrypt(String content) {
        switch (config.getArithmetic()) {
            case RSA:
                content = AirRsa.create().setPublicKey(config.getAppSecret()).decrypt(content);
            case AES:
                content = AirAes.create().setKey(config.getAppSecret()).decrypt(content);
                break;
            default:
        }
        return content;
    }

    /**
     * <h2>加密</h2>
     *
     * @param request 请求对象
     * @return 加密后的内容
     */
    public final <REQ extends AbstractRequest<RES>, RES extends AbstractResponse<RES>> String encrypt(REQ request) {
        return encrypt(AirJson.toString(request));
    }

    /**
     * <h2>加密</h2>
     *
     * @param content 需要加密的字符串
     * @return 加密后的内容
     */
    public final String encrypt(String content) {
        switch (config.getArithmetic()) {
            case RSA:
                content = AirRsa.create().setPublicKey(config.getPublicKey()).encrypt(content);
            case AES:
                content = AirAes.create().setKey(config.getAppSecret()).encrypt(content);
                break;
            default:
        }
        return content;
    }

    /**
     * <h2>创建AirPowerClient</h2>
     *
     * @param config 应用
     * @return AirPowerClient
     * @see AirConfig#create()
     */
    public static AirClient create(AirConfig config) {
        if (Objects.isNull(config)) {
            throw new IllegalArgumentException("无效的AirConfig配置");
        }
        AirClient client = new AirClient();
        client.config = config;
        return client;
    }
}
