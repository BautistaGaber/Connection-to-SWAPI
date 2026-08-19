package com.bautistagaber.connectiontoswapi.application.command;

/**
 * Immutable command DTO carrying user data for the registration use case.
 */
public record RegisterCommand(String username, String password) {
}
