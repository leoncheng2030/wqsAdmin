package com.wqs.core.util;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;

/**
 * 国密算法工具类
 * 包含 SM2 (非对称), SM3 (摘要), SM4 (对称)
 */
public class SmCryptoUtil {

    // SM2 默认公钥私钥 (仅作演示，实际生产请务必更换或动态生成)
    // 这里的密钥对可以通过 SmUtil.sm2().getPrivateKeyBase64() / getPublicKeyBase64() 生成
    private static final String SM2_PRIVATE_KEY = "MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgq+L...请替换为真实私钥...==";
    private static final String SM2_PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAE...请替换为真实公钥...==";
    
    // SM4 默认密钥 (128位，16字节)
    private static final String SM4_KEY = "1234567890123456";

    /**
     * SM3 摘要加密 (类似 MD5)
     * @param str 待加密字符串
     * @return 加密后 Hex 字符串
     */
    public static String sm3(String str) {
        return SmUtil.sm3(str);
    }

    /**
     * SM4 对称加密
     * @param data 待加密内容
     * @return 加密后 Hex 字符串
     */
    public static String sm4Encrypt(String data) {
        SM4 sm4 = SmUtil.sm4(SM4_KEY.getBytes());
        return sm4.encryptHex(data);
    }

    /**
     * SM4 对称解密
     * @param hexData 加密后的 Hex 字符串
     * @return 解密后原始内容
     */
    public static String sm4Decrypt(String hexData) {
        SM4 sm4 = SmUtil.sm4(SM4_KEY.getBytes());
        return sm4.decryptStr(hexData);
    }
    
    /**
     * SM2 公钥加密
     * @param data 待加密内容
     * @return 加密后 Hex 字符串
     */
    /* 需要配置真实的公私钥才能取消注释正常使用
    public static String sm2Encrypt(String data) {
        SM2 sm2 = SmUtil.sm2(SM2_PRIVATE_KEY, SM2_PUBLIC_KEY);
        return sm2.encryptHex(data, KeyType.PublicKey);
    }
    */

    /**
     * SM2 私钥解密
     * @param hexData 加密后的 Hex 字符串
     * @return 解密后原始内容
     */
    /*
    public static String sm2Decrypt(String hexData) {
        SM2 sm2 = SmUtil.sm2(SM2_PRIVATE_KEY, SM2_PUBLIC_KEY);
        return sm2.decryptStr(hexData, KeyType.PrivateKey);
    }
    */
}
