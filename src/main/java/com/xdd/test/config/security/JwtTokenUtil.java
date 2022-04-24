package com.xdd.test.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static String CLAIM_KEY_USERNAME = "username";
    private static String CLAIM_KEY_CREATED = "createTime";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 根据数据声明生成token令牌
     * @param claims 数据声明
     * @return token令牌
     */
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 根据数据声明生成token令牌
     * @param claims 数据声明
     * @return token令牌
     */
    public String generateToken(Claims claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token token令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token){
        String username;
        try {
            Claims claims=getClaimsFromToken(token);
            username=claims.getSubject();
        }catch (Exception e){
            return null;
        }
        return username;
    }
    /**
     * 从token中获取数据声明
     * @param token token令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(String token){
        Claims claims;
        try {
            claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return claims;
    }

    /**
     * 校验token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        try {
            Claims claims=getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 刷新token令牌
     * @param token
     * @return
     */
    public String refreshToken(String token){
        String refreshToken;
        try {
            Claims claims=getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED,new Date());
            refreshToken=generateToken(claims);
        }catch (Exception e){
            return null;
        }
        return refreshToken;
    }
}
