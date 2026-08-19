package com.bautistagaber.connectiontoswapi.presentation.dto.response;

/**
 * Response DTO for the login endpoint containing the JWT token.
 */
public record LoginResponse(String token) {
}
