package com.bautistagaber.connectiontoswapi.presentation.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " with id " + id + " not found");
    }

    public ResourceNotFoundException(String resource, String name) {
        super(resource + " with name '" + name + "' not found");
    }
}
