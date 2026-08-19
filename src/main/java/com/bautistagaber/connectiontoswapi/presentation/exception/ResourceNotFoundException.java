package com.bautistagaber.connectiontoswapi.presentation.exception;

/**
 * Thrown when a SWAPI resource is not found by ID or by name.
 * Mapped to HTTP 404 by GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " with id " + id + " not found");
    }

    public ResourceNotFoundException(String resource, String name) {
        super(resource + " with name '" + name + "' not found");
    }
}
