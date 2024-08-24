package com.xmxisme.product.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author Administrator
 */
public class JwtTokenParser {
    private static final String SECRET_KEY = "f8d3b6a9d18d7f54e9148d849e73a54846b8a9e3c7f0b7a5a7c7e1b6d4e2f3a1";

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getRoleNameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("roleName", String.class);
    }

}