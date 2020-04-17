package com.nahib.login.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JwtUtils {
    /**
     * 过期时间为一小时
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 60*60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "nahib";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @return
     */
    public static String createToken(String username){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        String token = JWT.create()
                .withHeader(header)//设置头部信息
                .withClaim("loginName",username)//核载信息
                .withIssuedAt(new Date(System.currentTimeMillis()))//设置生成签名时间
                .withExpiresAt(date)//设置签名过期时间
                .sign(algorithm);//签名 Signature，使用algorithm的算法进行加密
        return token;
    }

    /**
     * token验证方法
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

}
