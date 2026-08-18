package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;

import java.util.List;
import java.util.Optional;

public interface StarshipService {
    PageResult<Starship> findStarship(int page, int size, String name);

    Optional<Starship> findStarshipById(Long id);
}
