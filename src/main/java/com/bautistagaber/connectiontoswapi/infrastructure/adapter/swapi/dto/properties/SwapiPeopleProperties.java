package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record SwapiPeopleProperties(String name,
                                    @JsonProperty("birth_year")
                                    String birthYear,
                                    @JsonProperty("eye_color")
                                    String eyeColor,
                                    List<String> films,
                                    String gender,
                                    @JsonProperty("hair_color")
                                    String hairColor,
                                    String height,
                                    String homeworld,
                                    String mass,
                                    @JsonProperty("skin_color")
                                    String skinColor,
                                    Instant created,
                                    Instant edited,
                                    List<String> species,
                                    List<String> starships,
                                    String url,
                                    List<String> vehicles) {
}
