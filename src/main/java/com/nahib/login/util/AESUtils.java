package com.nahib.login.util;


import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

    static final String ALGORITHM = "AES";

    //生成SecretKey
    public static String generatKey() throws NoSuchAlgorithmException {
        //定义生成ALGORITHM加密算法的keyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        //生成随机数
        SecureRandom secureRandom = new SecureRandom();
        //初始化key生成器
        keyGenerator.init(secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        //的到秘钥字符串
        String secretKeyStr = new String(Base64.getEncoder().encode(secretKey.getEncoded()));
        return secretKeyStr;
    }

    //加密
    public static String encrypt(String content, String secretKeyStr) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //加密内容字节数组
        byte[] contentByteArray = Base64.getEncoder().encode(content.getBytes());
        //秘钥字节数组
        byte[] decoded = Base64.getDecoder().decode(secretKeyStr);
        SecretKeySpec secretKey = new SecretKeySpec(decoded,ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipher.ENCRYPT_MODE,secretKey);
        String result = Base64.getEncoder().encodeToString(cipher.doFinal(contentByteArray));
        return result;
    }

    //解密
    public static String decrypt(String content, String secretKeyStr) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //解码内容字节数组
        byte[] contentByteArray = Base64.getDecoder().decode(content);
        //解码秘钥字节数组
        byte[] decoded = Base64.getDecoder().decode(secretKeyStr);
        SecretKeySpec secretKey = new SecretKeySpec(decoded,ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipher.DECRYPT_MODE,secretKey);
        String result = new String(Base64.getDecoder().decode(cipher.doFinal(contentByteArray)));
        return result;
    }


    public static void main(String[] args){


        String p = "123";
        try {
            String secretKey = generatKey();
            System.out.println("秘钥"+secretKey);
            String en = AESUtils.encrypt(p,secretKey);
            System.out.println("加密"+ en);
            System.out.println( AESUtils.decrypt(en,secretKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
