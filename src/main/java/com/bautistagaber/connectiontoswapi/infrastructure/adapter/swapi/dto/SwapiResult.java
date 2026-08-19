package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

/**
 * DTO wrapper that wraps a single SWAPI resource with its uid and properties.
 */
public record SwapiResult<T> (String uid, String description, T properties){
}
