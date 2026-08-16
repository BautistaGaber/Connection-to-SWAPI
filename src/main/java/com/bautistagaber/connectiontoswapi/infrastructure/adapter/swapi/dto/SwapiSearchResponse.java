package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

import java.util.List;

public record SwapiSearchResponse<T>(String message, List<SwapiResult<T>> result) {
}
