package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO mapping SWAPI vehicle JSON properties from the external API.
 */
public record SwapiVehicleProperties(String created,
                                     String edited,
                                     String consumables,
                                     String name,

                                     @JsonProperty("cargo_capacity")
                                      String cargoCapacity,

                                     String passengers,

                                     @JsonProperty("max_atmosphering_speed")
                                      String maxAtmospheringSpeed,

                                     String crew,
                                     String length,
                                     String model,

                                     @JsonProperty("cost_in_credits")
                                      String costInCredits,

                                     String manufacturer,

                                     @JsonProperty("vehicle_class")
                                      String vehicleClass,

                                     List<String> pilots,
                                     List<String> films,
                                     String url) {
}
