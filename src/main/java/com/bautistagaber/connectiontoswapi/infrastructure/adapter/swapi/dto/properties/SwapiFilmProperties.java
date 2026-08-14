package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record SwapiFilmProperties(List<String> characters, Instant created, String director, Instant edited,
                                  @JsonProperty("episode_id") Integer episodeId,
                                  @JsonProperty("opening_crawl") String openingCrawl, List<String> planets,
                                  String producer, @JsonProperty("release_date") LocalDate releaseDate,
                                  List<String> species, List<String> starships, String title, String url,
                                  List<String> vehicles) {
}
