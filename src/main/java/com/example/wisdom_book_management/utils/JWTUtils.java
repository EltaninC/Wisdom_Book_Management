package com.example.wisdom_book_management.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;


public class JWTUtils {

    //token到期时间60s
    //private static final long EXPIRE_TIME= 60*1000;

    //密钥盐
    private static final String TOKEN_SECRET="ChEn";

    /**
     * 生成token  header.payload.singature
     */


    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        // 默认1天过期
        instance.add(Calendar.DATE, 1);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        String token = builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(TOKEN_SECRET));  // 密钥
        return token;
    }

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
    }

    /**
     * 获取token信息方法
     */
    /*public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }*/
}

