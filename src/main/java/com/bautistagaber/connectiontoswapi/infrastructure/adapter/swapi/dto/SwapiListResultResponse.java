package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO wrapper for SWAPI responses that return a list of generic results.
 * Used by search-by-name and detail endpoints.
 */
public record SwapiListResultResponse<T>(String message, @JsonProperty("result") List<SwapiResult<T>> result){ }
