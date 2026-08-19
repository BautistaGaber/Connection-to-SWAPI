package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import java.util.List;

/**
 * DTO mapping SWAPI starship JSON properties from the external API.
 */
public record SwapiStarshipProperties(String created,
                                      String edited,
                                      String consumables,
                                      String name,
                                      String cargo_capacity,
                                      String passengers,
                                      String max_atmosphering_speed,
                                      String crew,
                                      String length,
                                      String model,
                                      String cost_in_credits,
                                      String manufacturer,
                                      List<String> pilots,
                                      String MGLT,
                                      String starship_class,
                                      String hyperdrive_rating,
                                      List<String> films,
                                      String url) {
}
