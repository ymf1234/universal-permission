package com.github.ymf1234.common.utils;

import com.github.ymf1234.common.result.Jwt;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * 生产JSON Web令牌的工具类
 */
public class JwtHelper {

    // 设置token过期时间
    private static final long tokenExpiration = 365L * 24 * 60 * 60 * 1000;
    // 秘钥
    private static String tokenSignKey = "123456";

    /**
     * 生产token
     * @param claims
     * @return
     */
    public static String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject("JWT")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims ParesJWT(String token) {
        Claims claims = Jwts.parser().
                setSigningKey(tokenSignKey).
                parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            Claims claims = ParesJWT(token);
            Integer userId = (Integer) claims.get(Jwt.build().getUserId());
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return null;
            }
            Claims claims = ParesJWT(token);
            return (String) claims.get(Jwt.build().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
