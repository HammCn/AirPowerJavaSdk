package cn.hamm.sdk.common.base;

import cn.hamm.sdk.common.enums.AirErrorCode;
import cn.hamm.sdk.common.util.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>AirPowerClient</h1>
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
     * <h2>AppKey</h2>
     */
    private String appKey;

    /**
     * <h2>设置AppKey</h2>
     *
     * @param appKey AppKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * <h2>获取AppKey</h2>
     *
     * @return AppKey
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * <h2>版本号</h2>
     */
    private int version = 10000;

    /**
     * <h2>设置版本号</h2>
     *
     * @param version 版本号
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * <h2>获取版本号</h2>
     *
     * @return 版本号
     */
    public int getVersion() {
        return version;
    }

    /**
     * <h2>请求毫秒时间戳</h2>
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * <h2>设置请求毫秒时间戳</h2>
     *
     * @param timestamp 请求毫秒时间戳
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * <h2>获取请求毫秒时间戳</h2>
     *
     * @return 请求毫秒时间戳
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * <h2>加密后的业务数据</h2>
     */
    private String content;

    /**
     * <h2>设置加密后的业务数据</h2>
     *
     * @param content 加密后的业务数据
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <h2>获取加密后的业务数据</h2>
     *
     * @return 加密后的业务数据
     */
    public String getContent() {
        return content;
    }

    /**
     * <h2>Nonce</h2>
     */
    private String nonce = Random.randomString();

    /**
     * <h2>设置Nonce</h2>
     *
     * @param nonce Nonce
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * <h2>获取Nonce</h2>
     *
     * @return Nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * <h2>签名字符串</h2>
     */
    private String signature;

    /**
     * <h2>设置签名字符串</h2>
     *
     * @param signature 签名字符串
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * <h2>获取签名字符串</h2>
     *
     * @return 签名字符串
     */
    public String getSignature() {
        return signature;
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
    public final <REQ extends AbstractAirRequest<RES>, RES extends AbstractAirResponse<RES>> RES request(REQ request) {
        String content = Json.toString(request);
        switch (config.getArithmetic()) {
            case RSA:
                content = Rsa.create().setPublicKey(config.getPublicKey()).encrypt(content);
            case AES:
                content = Aes.create().setKey(config.getAppSecret()).encrypt(content);
                break;
            default:
        }
        this.appKey = config.getAppKey();
        this.content = content;
        this.signature = sign(config.getAppSecret());
        final String body = Json.toString(this);
        String response = Http.post(config.getGateway() + request.getApiUrl(), body);
        AirJson<?> airJson = Json.parse(response, AirJson.class);
        if (AirErrorCode.SUCCESS.getCode() != airJson.getCode()) {
            throw new AirException(airJson.getCode(), airJson.getMessage());
        }
        response = airJson.getData();
        switch (config.getArithmetic()) {
            case RSA:
                response = Rsa.create().setPublicKey(config.getAppSecret()).decrypt(response);
            case AES:
                response = Aes.create().setKey(config.getAppSecret()).decrypt(response);
                break;
            default:
        }
        Class<RES> responseClass = request.getResponseClass();
        return Json.parse(response, responseClass);
    }

    /**
     * <h2>创建AirPowerClient</h2>
     *
     * @param config 应用
     * @return AirPowerClient
     * @see AirConfig#create()
     */
    public static AirClient create(AirConfig config) {
        AirClient client = new AirClient();
        client.config = config;
        return client;
    }

    /**
     * <h2>签名</h2>
     *
     * @return 签名字符串
     */
    private String sign(String appSecret) {
        final String source = appSecret + this.appKey + this.version + this.timestamp + this.nonce + this.content;
        System.out.println(source);
        return DigestUtils.sha1Hex(source);
    }
}
