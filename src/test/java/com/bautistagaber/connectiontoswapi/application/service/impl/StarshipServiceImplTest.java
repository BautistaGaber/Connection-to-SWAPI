package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarshipServiceImplTest {

    @Mock
    private SwapiPort swapiPort;

    @InjectMocks
    private StarshipServiceImpl starshipService;

    @Test
    void findStarship_withName_returnsResults() {
        Starship starship = new Starship(1L, "Millennium Falcon", null, null, null,
                null, null, null, null, null, null, null, null, null, null, null,
                "https://swapi.tech/api/starships/10", null, null);
        PageResult<Starship> pageResult = new PageResult<>(List.of(starship), 0, 10, 1, 1);

        when(swapiPort.findStarshipByName("Falcon", 0, 10)).thenReturn(pageResult);

        PageResult<Starship> result = starshipService.findStarship(0, 10, "Falcon");

        assertEquals(1, result.content().size());
        assertEquals("Millennium Falcon", result.content().getFirst().getName());
    }

    @Test
    void findStarship_withNameEmpty_throwsResourceNotFoundException() {
        PageResult<Starship> emptyResult = new PageResult<>(List.of(), 0, 10, 0, 0);

        when(swapiPort.findStarshipByName("Invento", 0, 10)).thenReturn(emptyResult);

        assertThrows(ResourceNotFoundException.class,
                () -> starshipService.findStarship(0, 10, "Invento"));
    }

    @Test
    void findStarship_withoutName_returnsPaginatedResults() {
        Starship starship = new Starship(1L, "Millennium Falcon", null, null, null,
                null, null, null, null, null, null, null, null, null, null, null,
                "https://swapi.tech/api/starships/10", null, null);
        PageResult<Starship> pageResult = new PageResult<>(List.of(starship), 0, 10, 36, 4);

        when(swapiPort.findStarships(0, 10)).thenReturn(pageResult);

        PageResult<Starship> result = starshipService.findStarship(0, 10, null);

        assertEquals(1, result.content().size());
        assertEquals(36, result.totalElements());
    }

    @Test
    void findStarshipById_returnsStarship() {
        Starship starship = new Starship(1L, "Millennium Falcon", null, null, null,
                null, null, null, null, null, null, null, null, null, null, null,
                "https://swapi.tech/api/starships/10", null, null);

        when(swapiPort.findStarshipById(1L)).thenReturn(Optional.of(starship));

        Optional<Starship> result = starshipService.findStarshipById(1L);

        assertTrue(result.isPresent());
        assertEquals("Millennium Falcon", result.get().getName());
    }

    @Test
    void findStarshipById_returnsEmpty_whenNotFound() {
        when(swapiPort.findStarshipById(999L)).thenReturn(Optional.empty());

        Optional<Starship> result = starshipService.findStarshipById(999L);

        assertTrue(result.isEmpty());
    }
}
