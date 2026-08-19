package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * Full starship response DTO with all fields for the REST API.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarshipResponse {
    private Long id;
    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private String length;
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    private String cargoCapacity;
    private String consumables;
    private String hyperdriveRating;
    private String MGLT;
    private String starshipClass;
    private List<String> pilots;
    private List<String> films;
    private String url;
    private Instant created;
    private Instant edited;
}
