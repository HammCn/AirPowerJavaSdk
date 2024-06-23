package cn.hamm.sdk.common.base;

import cn.hamm.sdk.common.util.AirRandom;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>模型基类</h1>
 *
 * @author Hamm.cn
 */
public class AirRequest {
    /**
     * <h2>AppKey</h2>
     */
    private String appKey;

    /**
     * <h2>设置AppKey</h2>
     *
     * @param appKey AppKey
     * @return AirRequest
     */
    public final AirRequest setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    /**
     * <h2>获取AppKey</h2>
     *
     * @return AppKey
     */
    public final String getAppKey() {
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
     * @return AirRequest
     */
    public final AirRequest setVersion(int version) {
        this.version = version;
        return this;
    }

    /**
     * <h2>获取版本号</h2>
     *
     * @return 版本号
     */
    public final int getVersion() {
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
     * @return AirRequest
     */
    public final AirRequest setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * <h2>获取请求毫秒时间戳</h2>
     *
     * @return 请求毫秒时间戳
     */
    public final long getTimestamp() {
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
     * @return AirRequest
     */
    public final AirRequest setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * <h2>获取加密后的业务数据</h2>
     *
     * @return 加密后的业务数据
     */
    public final String getContent() {
        return content;
    }

    /**
     * <h2>Nonce</h2>
     */
    private String nonce = AirRandom.randomString();

    /**
     * <h2>设置Nonce</h2>
     *
     * @param nonce Nonce
     * @return AirRequest
     */
    public final AirRequest setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    /**
     * <h2>获取Nonce</h2>
     *
     * @return Nonce
     */
    public final String getNonce() {
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
     * @return AirRequest
     */
    public final AirRequest setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * <h2>获取签名字符串</h2>
     *
     * @return 签名字符串
     */
    public final String getSignature() {
        return signature;
    }

    /**
     * <h2>签名</h2>
     *
     * @param appSecret AppSecret
     * @return AirRequest
     */
    public final AirRequest sign(String appSecret) {
        final String source = appSecret + getAppKey() + getVersion() + getTimestamp() + getNonce() + getContent();
        System.out.println(source);
        this.signature = DigestUtils.sha1Hex(source);
        return this;
    }
}
