package com.bautistagaber.connectiontoswapi.presentation.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Object identifier) {
        super(resource + " with id " + identifier + " not found");
    }
}
