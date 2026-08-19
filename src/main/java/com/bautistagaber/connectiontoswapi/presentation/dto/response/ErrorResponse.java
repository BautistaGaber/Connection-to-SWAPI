package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

import java.time.Instant;

/**
 * Standard error response DTO with timestamp, status, error, message, and path.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
