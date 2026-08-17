package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.FilmService;
import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.FilmResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.FilmResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponse> findFilmById(@PathVariable Long id) {

        return filmService.findFilmById(id)
                .map(filmResponseMapper::toResponse)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public ResponseEntity<List<FilmResponse>> findFilmByName(@RequestParam String name) {
        List<FilmResponse> film = filmService.findFilmByName(name)
                .stream()
                .map(filmResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(film);
    }
}
