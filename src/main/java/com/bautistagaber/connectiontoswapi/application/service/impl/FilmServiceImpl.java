package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.FilmService;
import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the film service.
 * Delegates queries to SwapiPort and validates that name searches return results.
 */
@Service
public class FilmServiceImpl implements FilmService {

    private final SwapiPort swapiPort;

    public FilmServiceImpl(SwapiPort swapiPort){
        this.swapiPort = swapiPort;
    }

    @Override
    public PageResult<Film> findFilms(int page, int size, String title) {

        if (title == null || title.isBlank()) {
            return swapiPort.findFilms(page, size);
        }

        PageResult<Film> result = swapiPort.findFilmByName(title, page, size);

        if (result.content().isEmpty()) {
            throw new ResourceNotFoundException("Film", title);
        }

        return result;
    }

    @Override
    public Optional<Film> findFilmById(Long id) {
        return swapiPort.findFilmById(id);
    }
}
