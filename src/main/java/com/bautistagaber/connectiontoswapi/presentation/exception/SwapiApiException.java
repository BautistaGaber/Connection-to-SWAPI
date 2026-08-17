package com.bautistagaber.connectiontoswapi.presentation.exception;

public class SwapiApiException extends RuntimeException {
    public SwapiApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
