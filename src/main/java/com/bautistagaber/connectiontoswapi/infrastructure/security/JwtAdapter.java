package com.bautistagaber.connectiontoswapi.infrastructure.security;

import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtAdapter implements JwtPort {
    private static final String SECRET_KEY = "my-super-secret-key-that-is-at-least-256-bits-long";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; //tiempo que dura el token

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        String username = extractUsername(token);

        return username.equals(user.getUsername());
    }
}
