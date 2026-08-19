package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

    @Mock
    private SwapiPort swapiPort;

    @InjectMocks
    private FilmServiceImpl filmService;

    @Test
    void findFilms_withTitle_returnsResults() {
        Film film = new Film(1L, "A New Hope", 4, null, "George Lucas",
                "Gary Kurtz", LocalDate.of(1977, 5, 25), null, null, null, null, null,
                "https://swapi.tech/api/films/1", null, null);
        PageResult<Film> pageResult = new PageResult<>(List.of(film), 0, 10, 1, 1);

        when(swapiPort.findFilmByName("A New Hope", 0, 10)).thenReturn(pageResult);

        PageResult<Film> result = filmService.findFilms(0, 10, "A New Hope");

        assertEquals(1, result.content().size());
        assertEquals("A New Hope", result.content().getFirst().getTitle());
    }

    @Test
    void findFilms_withTitleEmpty_throwsResourceNotFoundException() {
        PageResult<Film> emptyResult = new PageResult<>(List.of(), 0, 10, 0, 0);

        when(swapiPort.findFilmByName("Invento", 0, 10)).thenReturn(emptyResult);

        assertThrows(ResourceNotFoundException.class,
                () -> filmService.findFilms(0, 10, "Invento"));
    }

    @Test
    void findFilms_withoutTitle_returnsPaginatedResults() {
        Film film = new Film(1L, "A New Hope", 4, null, "George Lucas",
                "Gary Kurtz", LocalDate.of(1977, 5, 25), null, null, null, null, null,
                "https://swapi.tech/api/films/1", null, null);
        PageResult<Film> pageResult = new PageResult<>(List.of(film), 0, 10, 6, 1);

        when(swapiPort.findFilms(0, 10)).thenReturn(pageResult);

        PageResult<Film> result = filmService.findFilms(0, 10, null);

        assertEquals(1, result.content().size());
        assertEquals(6, result.totalElements());
    }

    @Test
    void findFilmById_returnsFilm() {
        Film film = new Film(1L, "A New Hope", 4, null, "George Lucas",
                "Gary Kurtz", LocalDate.of(1977, 5, 25), null, null, null, null, null,
                "https://swapi.tech/api/films/1", null, null);

        when(swapiPort.findFilmById(1L)).thenReturn(Optional.of(film));

        Optional<Film> result = filmService.findFilmById(1L);

        assertTrue(result.isPresent());
        assertEquals("A New Hope", result.get().getTitle());
    }

    @Test
    void findFilmById_returnsEmpty_whenNotFound() {
        when(swapiPort.findFilmById(999L)).thenReturn(Optional.empty());

        Optional<Film> result = filmService.findFilmById(999L);

        assertTrue(result.isEmpty());
    }
}
