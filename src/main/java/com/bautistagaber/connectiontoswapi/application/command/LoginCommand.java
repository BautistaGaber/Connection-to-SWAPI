package com.bautistagaber.connectiontoswapi.application.command;

/**
 * Immutable command DTO carrying credentials for the login use case.
 */
public record LoginCommand(String username, String password) {
}
