package cn.hamm.sdk.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <h1>AES助手</h1>
 *
 * @author Hamm.cn
 */
public class Aes {
    /**
     * <h2>AES</h2>
     */
    private static final String AES = "AES";

    /**
     * <h2>AES/CBC/PKCS5Padding</h2>
     */
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * <h2>密钥</h2>
     */
    private byte[] key;

    /**
     * <h2>偏移向量</h2>
     */
    private static final byte[] IV = "0000000000000000".getBytes(StandardCharsets.UTF_8);

    /**
     * <h2>算法</h2>
     */
    private String algorithm = AES_CBC_PKCS5_PADDING;

    /**
     * <h2>设置算法</h2>
     *
     * @param algorithm 算法
     * @return AesUtil
     */
    public Aes setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    /**
     * <h2>设置密钥</h2>
     *
     * @param key 密钥
     * @return AesUtil
     */
    public Aes setKey(String key) {
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
            return Base64.getEncoder().encodeToString(getCipher(Cipher.ENCRYPT_MODE).doFinal(source.getBytes(StandardCharsets.UTF_8)));
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
            return new String(getCipher(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder().decode(content)), StandardCharsets.UTF_8);
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

    /**
     * <h2>初始化</h2>
     *
     * @return AES
     */
    public static Aes create() {
        return new Aes();
    }
}
