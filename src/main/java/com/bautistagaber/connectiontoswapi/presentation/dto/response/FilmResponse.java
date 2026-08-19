package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * Full film response DTO with all fields for the REST API.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse {
    private Long id;
    private String title;
    private Integer episodeId;
    private String openingCrawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;
    private List<String> species;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> characters;
    private List<String> planets;
    private String url;
    private Instant created;
    private Instant edited;
}
