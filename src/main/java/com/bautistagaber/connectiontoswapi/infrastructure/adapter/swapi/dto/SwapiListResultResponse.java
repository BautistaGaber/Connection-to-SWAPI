package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SwapiListResultResponse<T>(String message, @JsonProperty("result") List<SwapiResult<T>> result){ }
