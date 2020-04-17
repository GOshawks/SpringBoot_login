package com.nahib.login.util;

import javax.crypto.*;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

public class RSAUtils {
    private static final String ALGORITHM = "RSA";
    private static final Integer PUBLICKEY = 0;
    private static final Integer PRIVATE  = 1;
    //key初始化大小主流可选值：1024、2048、3072、4096...
    private static final int KEYSIZE = 1024;
    private static Charset charset = Charset.forName("UTF-8");
    //生成KeyPair
    public static Map<Integer,String> generatKeyPair() throws NoSuchAlgorithmException {
        //定义生成ALGORITHM加密算法的keyGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        //生成随机数
        SecureRandom secureRandom = new SecureRandom();
        //初始化key生成器,主流可选值：1024、2048、3072、4096...
        keyPairGenerator.initialize(KEYSIZE,secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥

        //得到公钥字符串
        String publicKeyString = new String(Base64.getEncoder().encode(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.getEncoder().encode((privateKey.getEncoded())));

        // 将公钥和私钥保存到Map
        Map<Integer,String > keyMap = new HashMap();
        keyMap.put(PUBLICKEY,publicKeyString);  //0表示公钥
        keyMap.put(PRIVATE,privateKeyString);  //1表示私钥
        return keyMap;
    }



    /**
     * RSA公钥加密
     *
     * @param content
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     */
    public static String encrypt(String content, String publicKey) throws Exception {
        //使用base64对内编码
        byte[] contentByteArray = Base64.getEncoder().encode(content.getBytes(charset));
        //base64解码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(contentByteArray));
        System.out.println("按"+outStr);
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param content
     *            解密字符串
     * @param privateKey
     *            私钥
     * @return 密文
     */
    public static String decrypt(String content, String privateKey) throws Exception{
        //64位解码需要解密的字符串
        byte[] inputByte = Base64.getDecoder().decode(content.getBytes());
        //base64解码码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(Base64.getDecoder().decode(cipher.doFinal(inputByte)));
        return outStr;
    }


    public static void main(String[] args) throws Exception {
       // String publicKey ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM5Gg5JbaBseXqz9iZLWNbFv3nhf+N+VnZ0rrmgz8HGKLYhtrzQiIL35JrWmGY5UMCT4hUOw81n5gnZCDSE+agtIvF5/YDrJIk+Xmy7xNoY65VmDVJHKTnluMfKZBSfGvIP+aXUSYFRoZyuz0UKJUHLfySB5k1sJ7G5xVDGfo5iXAgMBAAECgYEAk6KQZN4bQt2XsYS9RGUghOCmf81g2NXCu00aROZ3vyvArxaiAVQzzwRWGkjJnb7PvoZJC0vIwKr+HxnjP9nmFufd+0EnBT+imYSzrfZhfGGwyI6EIyy/XcoW5lf0xltx3w9mJicnR9kMzNtZ5mNGPMNnCgAgjvZqnWYb+f6tb/ECQQD0tdpg8ts3puXclPe51my+LbKhEbyFSMzvtMTDCRmOd0jrmZhQomsZacC8+l+2l6WTj5vrhVQlAVUeUJ7kldQNAkEA18q53wor6a4Cv0OLxFzBWXRCMVFfyCWAFQUpTSGrIM/X4Lx30IZCShtvkdh1ky39b9T6lpOjES7MK4DhxttCMwJAUGBi6DEcm/zvxzIO5DVv5k9wOsNunoC4/4rqjf0xLcA0bV43z1RpxSEdM3UxdvH8aqli10slxjnX0Ws9pWspCQJBALqSncgYzETbXaauqO5a4BUOrphjafPrcGU8NCxrGsFg0p6NdO5G1pOqSvmHdIiPL9t8AjkkZs3Zb0+BvDOpqP8CQQDZhfh4/c/Qzp4szj7+GXTZ1cmGwAuFo2/9uiumUAS3f19EpgoV9u9eyJ4gZPEBDvAjO961kAjdja4DAy4SbCXy";
       // String str = "onQOesx/u/1+Ap2N1RwMDqjuC77Y7DEcViBNqdF1Tk1rEO4BqdcU65uuvj+4rYtwO+QdTD3VZMl88ujqo9gaeB2FBXC8zaiLN6TMKu9M59hnHqlwOqCwbhaYzY+wgH7M+iuRoRuv5DrYyLu79FJOz9RJV4PUIjWsrRgZzxcZYKY=";

        String str = "dssdafasd";
        Map<Integer,String> map = RSAUtils.generatKeyPair();
        System.out.println("生成公钥："+ map.get(PUBLICKEY));
        System.out.println("生成私钥："+ map.get(PRIVATE));
        String pubStr = RSAUtils.encrypt(str,map.get(PUBLICKEY));

        String priStr = RSAUtils.decrypt(pubStr,map.get(PRIVATE));



    }
}
