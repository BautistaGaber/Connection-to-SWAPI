package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

/**
 * DTO wrapper for single SWAPI resource responses (detail by ID).
 */
public record SwapiResponse<T>(String message, SwapiResult<T> result) {
}
