package com.bautistagaber.connectiontoswapi.presentation.exception;

/**
 * Thrown when the external SWAPI API returns an error or is unreachable.
 * Wraps the original cause. Mapped to HTTP 502 by GlobalExceptionHandler.
 */
public class SwapiApiException extends RuntimeException {
    public SwapiApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
