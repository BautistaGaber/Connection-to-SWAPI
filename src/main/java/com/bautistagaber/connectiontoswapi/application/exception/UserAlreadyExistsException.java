package com.bautistagaber.connectiontoswapi.application.exception;

/**
 * Thrown when a registration attempt uses a username that already exists.
 * Mapped to HTTP 409 by GlobalExceptionHandler.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
