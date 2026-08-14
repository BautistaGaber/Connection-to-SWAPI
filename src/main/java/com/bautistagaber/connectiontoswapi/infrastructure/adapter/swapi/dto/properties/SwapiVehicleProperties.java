package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record SwapiVehicleProperties(@JsonProperty("cargo_capacity") String cargoCapacity, String consumables,
                                     @JsonProperty("cost_in_credits") String costInCredits, Instant created,
                                     String crew, Instant edited, String length, String manufacturer,
                                     @JsonProperty("max_atmosphering_speed") String maxAtmospheringSpeed, String model,
                                     String name, String passengers, List<String> pilots, String url,
                                     @JsonProperty("vehicle_class") String vehicleClass, List<String> films) {
}
