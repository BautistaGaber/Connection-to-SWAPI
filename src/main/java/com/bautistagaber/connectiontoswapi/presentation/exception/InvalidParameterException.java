package com.bautistagaber.connectiontoswapi.presentation.exception;

/**
 * Thrown for invalid request parameters.
 * Mapped to HTTP 400 by GlobalExceptionHandler.
 */
public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message) {
        super(message);
    }
}
