package cn.hamm.sdk.common.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * <h1>Air RSA</h1>
 *
 * @author Hamm.cn
 */
public class AirRsa {
    /**
     * <h2>加密算法KEY长度</h2>
     */
    private static final int CRYPT_KEY_SIZE = 2048;

    /**
     * <h2>加密方式</h2>
     */
    private static final String CRYPT_METHOD = "RSA";

    /**
     * <h2>公钥</h2>
     */
    private String publicKey;

    /**
     * <h2>初始化</h2>
     *
     * @return RSA
     */
    public static AirRsa create() {
        return new AirRsa();
    }

    /**
     * <h2>设置公钥</h2>
     *
     * @param publicKey 公钥
     * @return 实例
     */
    public final AirRsa setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    /**
     * <h2>公钥加密</h2>
     *
     * @param sourceContent 原文
     * @return 密文
     */
    public final String encrypt(String sourceContent) {
        try {
            int blockSize = CRYPT_KEY_SIZE / 8 - 11;
            PublicKey publicKey = getPublicKey(this.publicKey);
            return encrypt(sourceContent, publicKey, blockSize);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * <h2>公钥解密</h2>
     *
     * @param encryptedContent 密文
     * @return 原文
     */
    public final String decrypt(String encryptedContent) {
        try {
            int blockSize = CRYPT_KEY_SIZE / 8;
            PublicKey publicKey = getPublicKey(this.publicKey);
            return decrypt(encryptedContent, publicKey, blockSize);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * <h2>公私钥解密</h2>
     *
     * @param encryptedContent 密文
     * @param key              公私钥
     * @param blockSize        分块大小
     * @return 明文
     */
    private String decrypt(String encryptedContent, Key key, int blockSize) throws Exception {
        byte[] srcBytes = Base64.getDecoder().decode(encryptedContent);
        Cipher deCipher;
        deCipher = Cipher.getInstance(CRYPT_METHOD);
        deCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] resultBytes;
        resultBytes = rsaDoFinal(deCipher, srcBytes, blockSize);
        return new String(resultBytes);
    }

    /**
     * <h2>公私钥加密</h2>
     *
     * @param sourceContent 明文
     * @param key           公私钥
     * @param blockSize     区块大小
     * @return 密文
     */
    private String encrypt(String sourceContent, Key key, int blockSize) throws Exception {
        byte[] srcBytes = sourceContent.getBytes();
        Cipher cipher;
        cipher = Cipher.getInstance(CRYPT_METHOD);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] resultBytes;
        resultBytes = rsaDoFinal(cipher, srcBytes, blockSize);
        return Base64.getEncoder().encodeToString(resultBytes);
    }

    /**
     * <h2>获取一个公钥</h2>
     *
     * @param publicKeyString 公钥字符串
     * @return 公钥
     * @throws Exception 异常
     */
    private PublicKey getPublicKey(String publicKeyString) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(CRYPT_METHOD);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * <h2>RSA处理方法</h2>
     *
     * @param cipher      RSA实例
     * @param sourceBytes 加解密原始数据
     * @param blockSize   分片大小
     * @return 加解密结果
     * @throws Exception 加解密异常
     */
    private byte[] rsaDoFinal(Cipher cipher, byte[] sourceBytes, int blockSize) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int inputLength = sourceBytes.length;
        int currentOffSet = 0;
        byte[] cacheBytes;
        int index = 0;
        // 对数据分段解密
        while (inputLength - currentOffSet > 0) {
            cacheBytes = cipher.doFinal(sourceBytes, currentOffSet, Math.min(inputLength - currentOffSet, blockSize));
            byteArrayOutputStream.write(cacheBytes, 0, cacheBytes.length);
            index++;
            currentOffSet = index * blockSize;
        }
        byte[] data = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return data;
    }
}
