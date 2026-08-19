package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.FilmService;
import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.FilmResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.FilmResponseMapper;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for films. Exposes GET /api/films (paginated list with optional title filter)
 * and GET /api/films/{id}. Requires JWT authentication.
 */
@RestController
@RequestMapping("/api/films")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class FilmController {

    private final FilmService filmService;
    private final FilmResponseMapper filmResponseMapper;

    public FilmController(FilmService filmService, FilmResponseMapper filmResponseMapper) {
        this.filmService = filmService;
        this.filmResponseMapper = filmResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<FilmResponse>> findFilms(@RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size, @RequestParam(required = false) String title) {

        PageResult<Film> result = filmService.findFilms(page, size, title);

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
    public ResponseEntity<FilmResponse> findFilmById(@PathVariable @Positive Long id) {

        return filmService.findFilmById(id)
                .map(filmResponseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Film", id));
    }
}
