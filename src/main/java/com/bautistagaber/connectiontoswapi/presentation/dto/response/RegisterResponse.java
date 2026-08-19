package com.bautistagaber.connectiontoswapi.presentation.dto.response;

/**
 * Response DTO for the registration endpoint containing id and username.
 */
public record RegisterResponse(Long id,
                               String username) {
}
