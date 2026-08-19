package com.bautistagaber.connectiontoswapi.presentation.dto.response;

/**
 * Request DTO for the login endpoint (username, password).
 */
public record LoginRequest(String username, String password) {
}
