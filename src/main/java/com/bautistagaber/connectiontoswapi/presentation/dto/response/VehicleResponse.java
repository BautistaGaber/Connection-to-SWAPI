package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

/**
 * Full vehicle response DTO with all fields for the REST API.
 */
@Getter
@Builder
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String name;
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String length;
    private String costInCredits;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String cargoCapacity;
    private String consumables;
    private List<String> films;
    private List<String> pilots;
    private String url;
    private Instant created;
    private Instant edited;
}
