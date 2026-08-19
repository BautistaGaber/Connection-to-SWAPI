package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.StarshipService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the starship service.
 * Delegates queries to SwapiPort and validates that name searches return results.
 */
@Service
public class StarshipServiceImpl implements StarshipService {
    private final SwapiPort swapiPort;

    public StarshipServiceImpl(SwapiPort swapiPort) {
        this.swapiPort = swapiPort;
    }

    @Override
    public PageResult<Starship> findStarship(int page, int size, String name) {

        if (name == null || name.isBlank()) {
            return swapiPort.findStarships(page, size);
        }

        PageResult<Starship> result = swapiPort.findStarshipByName(name, page, size);

        if (result.content().isEmpty()) {
            throw new ResourceNotFoundException("Starship", name);
        }

        return result;

    }

    @Override
    public Optional<Starship> findStarshipById(Long id) {
        return swapiPort.findStarshipById(id);
    }
}
