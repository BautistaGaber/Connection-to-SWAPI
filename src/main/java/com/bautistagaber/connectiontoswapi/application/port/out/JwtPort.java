package com.bautistagaber.connectiontoswapi.application.port.out;

import com.bautistagaber.connectiontoswapi.domain.user.User;

public interface JwtPort {
    String generateToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);
}
