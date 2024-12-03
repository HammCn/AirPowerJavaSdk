package cn.hamm.sdk.common.base;

import cn.hamm.sdk.common.util.AirDebug;
import cn.hamm.sdk.common.util.AirRandom;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <h1>AirRequest</h1>
 *
 * @author Hamm.cn
 */
public class AirRequest {
    /**
     * <h2>AppKey</h2>
     */
    private String appKey;

    /**
     * <h2>版本号</h2>
     */
    private int version = 10000;

    /**
     * <h2>请求毫秒时间戳</h2>
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * <h2>加密后的业务数据</h2>
     */
    private String content;

    /**
     * <h2>Nonce</h2>
     */
    private String nonce = AirRandom.randomString();

    /**
     * <h2>签名字符串</h2>
     */
    private String signature;

    /**
     * <h2>获取AppKey</h2>
     *
     * @return AppKey
     */
    public final String getAppKey() {
        return appKey;
    }

    /**
     * <h2>设置AppKey</h2>
     *
     * @param appKey AppKey
     * @return 当前实例
     */
    public final AirRequest setAppKey(String appKey) {
        this.appKey = appKey;
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
     * <h2>设置版本号</h2>
     *
     * @param version 版本号
     * @return 当前实例
     */
    public final AirRequest setVersion(int version) {
        this.version = version;
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
     * <h2>设置请求毫秒时间戳</h2>
     *
     * @param timestamp 请求毫秒时间戳
     * @return 当前实例
     */
    public final AirRequest setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
     * <h2>设置加密后的业务数据</h2>
     *
     * @param content 加密后的业务数据
     * @return 当前实例
     */
    public final AirRequest setContent(String content) {
        this.content = content;
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
     * <h2>设置Nonce</h2>
     *
     * @param nonce Nonce
     * @return 当前实例
     */
    public final AirRequest setNonce(String nonce) {
        this.nonce = nonce;
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
     * <h2>设置签名字符串</h2>
     *
     * @param signature 签名字符串
     * @return 当前实例
     */
    public final AirRequest setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * <h2>签名</h2>
     *
     * @param appSecret AppSecret
     */
    public final void sign(String appSecret) {
        String[] strings = new String[]{appSecret, getAppKey(), String.valueOf(getVersion()), String.valueOf(getTimestamp()), getNonce(), getContent()};
        final String source = String.join("", strings);
        AirDebug.show("签名数据", source);
        AirDebug.show("签名数据(可读)", String.join("  ", strings));
        this.signature = DigestUtils.sha1Hex(source);
        AirDebug.show("签名结果", this.signature);
    }
}
