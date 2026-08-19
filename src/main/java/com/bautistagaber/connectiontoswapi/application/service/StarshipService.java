package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;

import java.util.Optional;

/**
 * Service interface for starships: paginated queries and search by ID.
 */
public interface StarshipService {
    PageResult<Starship> findStarship(int page, int size, String name);

    Optional<Starship> findStarshipById(Long id);
}
