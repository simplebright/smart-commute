package com.example.smart_commute.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // 生成密钥
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, String roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())  // 使用新API
                .compact();
    }

    public Claims parseToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // 使用新API
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}