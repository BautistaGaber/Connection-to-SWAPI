package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.FilmService;
import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.FilmResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleListResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.FilmResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;
import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;
    private final FilmResponseMapper filmResponseMapper;

    public FilmController(FilmService filmService, FilmResponseMapper filmResponseMapper) {
        this.filmService = filmService;
        this.filmResponseMapper = filmResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<FilmResponse>> findFilms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
    {
        PageResult<Film> result = filmService.findFilms(page, size);

        PageResponse<FilmResponse> response = new PageResponse<>(
                result.content().stream().map(filmResponseMapper::toResponse).toList(),
                result.page(),
                result.size(),
                result.totalElements(),
                result.totalPages()
        );

        return ResponseEntity.ok(response);
    }
}
