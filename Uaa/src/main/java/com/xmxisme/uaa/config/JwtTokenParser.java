package com.xmxisme.uaa.config;

import com.xmxisme.uaa.pojo.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Administrator
 */
public class JwtTokenParser {
    private static final String SECRET_KEY = "f8d3b6a9d18d7f54e9148d849e73a54846b8a9e3c7f0b7a5a7c7e1b6d4e2f3a1";

    public static String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .claim("password", user.getPassword())
                .claim("roleName", user.getRoleName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

}
