package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto;

public record SwapiResult<T> (String uid, String description, T properties){
}
