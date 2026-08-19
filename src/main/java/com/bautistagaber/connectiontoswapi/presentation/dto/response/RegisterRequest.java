package com.bautistagaber.connectiontoswapi.presentation.dto.response;

/**
 * Request DTO for the registration endpoint (username, password).
 */
public record RegisterRequest(String username,
                              String password){
}
