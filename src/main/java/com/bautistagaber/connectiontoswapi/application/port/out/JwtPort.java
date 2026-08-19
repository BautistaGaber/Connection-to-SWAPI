package com.bautistagaber.connectiontoswapi.application.port.out;

import com.bautistagaber.connectiontoswapi.domain.user.User;

/**
 * Outbound port for JWT operations: generate token, extract username, validate token.
 * Implemented by JwtAdapter in the infrastructure layer.
 */
public interface JwtPort {
    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);
}
