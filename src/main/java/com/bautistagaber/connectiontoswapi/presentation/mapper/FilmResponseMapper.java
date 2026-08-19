package com.bautistagaber.connectiontoswapi.presentation.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.FilmResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper that converts the domain Film model into the FilmResponse DTO.
 */
@Component
public class FilmResponseMapper {
    public FilmResponse toResponse(Film film){
        return FilmResponse.builder()
                .id(film.getId())
                .title(film.getTitle())
                .episodeId(film.getEpisodeId())
                .openingCrawl(film.getOpeningCrawl())
                .director(film.getDirector())
                .producer(film.getProducer())
                .releaseDate(film.getReleaseDate())
                .species(film.getSpecies())
                .starships(film.getStarships())
                .vehicles(film.getVehicles())
                .characters(film.getCharacters())
                .planets(film.getPlanets())
                .url(film.getUrl())
                .created(film.getCreated())
                .edited(film.getEdited())
                .build();
    }
}
