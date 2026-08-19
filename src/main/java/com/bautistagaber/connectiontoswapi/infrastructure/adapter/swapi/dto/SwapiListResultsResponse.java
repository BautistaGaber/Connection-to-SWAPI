package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO wrapper for SWAPI paginated list responses with totalRecords, totalPages, and results.
 */
public record SwapiListResultsResponse<T> (String message,
                                           @JsonProperty("total_records") Integer totalRecords,
                                           @JsonProperty("total_pages") Integer totalPages,
                                           String previous,
                                           String next,
                                           List<T> results){
}
