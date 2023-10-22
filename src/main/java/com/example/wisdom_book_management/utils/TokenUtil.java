package com.example.wisdom_book_management.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;

/**
 * @author laz
 * @date 2022/09/09 14:55
 */
public class TokenUtil {


    //token到期时间60min
    private static final long EXPIRE_TIME= 24*60*1000;
    //密钥盐
    private static final String TOKEN_SECRET="ChEn";

    /**
     * 创建一个token
     * @return
     */
    public static String sign(Map<String, String> map){
        String token = null;
        try {
            Date expireAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);

            //创建jwt builder
            JWTCreator.Builder builder = JWT.create();

            // payload
            map.forEach((k, v) -> {
                builder.withClaim(k, v);
            });

            token = builder.withExpiresAt(expireAt)  //指定令牌过期时间
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));  // 密钥

        } catch (IllegalArgumentException|JWTCreationException je) {

        }
        return token;
    }
    /**
     * 对token进行验证
     * @param token
     * @return
     */
    public static Boolean verify(String token){
        try {
            //创建token验证器
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
//            System.out.println("认证通过：");
//            System.out.println("username: " + TokenUtil.getUserId(token));
//            System.out.println("过期时间：    " + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException |JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

    /**
     * 获取用户id
     */
    public static String getUserId(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return  jwt.getClaim("user_id").asString();

        }catch (JWTDecodeException e)
        {
            return null;
        }
    }

    /**
     * 获取权限类型
     */
    public static String getRole(String token){
        try{
            DecodedJWT jwt=JWT.decode(token);
            return  jwt.getClaim("role").asString();
        }catch (JWTDecodeException e)
        {
            return null;
        }
    }

    /**
     * role权限判断
     */
    public static boolean isAdmin(HttpServletRequest request){
        String token = request.getHeader("token");
        String role = TokenUtil.getRole(token);
        if(role.equals("a")){
            return true;
        }
        return false;
    }
}
