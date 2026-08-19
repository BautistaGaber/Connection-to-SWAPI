package com.bautistagaber.connectiontoswapi.application.exception;

/**
 * Thrown when login fails due to a non-existent username or incorrect password.
 * Mapped to HTTP 401 by GlobalExceptionHandler.
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
