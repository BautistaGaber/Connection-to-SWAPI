package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    PageResult<Film> findFilms(int page, int size,String title);

    Optional<Film> findFilmById(Long id);
}
