package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list;


/**
 * Lightweight DTO for SWAPI list endpoints (people, starships, vehicles) with uid, name, and url.
 */
public record SwapiListItem(String uid, String name, String url) { }
