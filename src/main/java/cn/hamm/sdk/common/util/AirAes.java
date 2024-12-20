package cn.hamm.sdk.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <h1>AirAes</h1>
 *
 * @author Hamm.cn
 */
public class AirAes {
    /**
     * <h2>AES</h2>
     */
    private static final String AES = "AES";

    /**
     * <h2>AES/CBC/PKCS5Padding</h2>
     */
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * <h2>偏移向量</h2>
     */
    private static final byte[] IV = "0000000000000000".getBytes(UTF_8);

    /**
     * <h2>密钥</h2>
     */
    private byte[] key;

    /**
     * <h2>算法</h2>
     */
    private String algorithm = AES_CBC_PKCS5_PADDING;

    /**
     * <h2>初始化</h2>
     *
     * @return AES
     */
    public static AirAes create() {
        return new AirAes();
    }

    /**
     * <h2>设置算法</h2>
     *
     * @param algorithm 算法
     * @return 当前实例
     */
    public AirAes setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    /**
     * <h2>设置密钥</h2>
     *
     * @param key 密钥
     * @return 当前实例
     */
    public AirAes setKey(String key) {
        this.key = Base64.getDecoder().decode(key);
        return this;
    }

    /**
     * <h2>加密</h2>
     *
     * @param source 待加密的内容
     * @return 加密后的内容
     */
    public final String encrypt(String source) {
        try {
            byte[] bytes = source.getBytes(UTF_8);
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>解密</h2>
     *
     * @param content 加密后的内容
     * @return 解密后的内容
     */
    public final String decrypt(String content) {
        try {
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            byte[] decodeContent = Base64.getDecoder().decode(content);
            return new String(cipher.doFinal(decodeContent), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h2>获取Cipher</h2>
     *
     * @param mode 模式
     * @return Cipher
     */
    private Cipher getCipher(int mode) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, secretKeySpec, ivParameterSpec);
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
