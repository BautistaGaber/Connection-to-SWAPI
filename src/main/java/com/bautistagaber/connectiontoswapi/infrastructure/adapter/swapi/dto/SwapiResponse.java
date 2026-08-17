package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

public record SwapiResponse<T>(String message, SwapiResult<T> result) {
}
