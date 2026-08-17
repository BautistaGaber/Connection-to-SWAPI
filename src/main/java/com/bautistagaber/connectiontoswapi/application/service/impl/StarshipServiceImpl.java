package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.StarshipService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarshipServiceImpl implements StarshipService {
    private final SwapiPort swapiPort;

    public StarshipServiceImpl(SwapiPort swapiPort) {
        this.swapiPort = swapiPort;
    }

    @Override
    public PageResult<Starship> findStarship(int page, int size) {
        return swapiPort.findStarships(page, size);
    }

    @Override
    public Optional<Starship> findStarshipById(Long id) {
        return swapiPort.findStarshipById(id);
    }

    @Override
    public List<Starship> findStarshipByName(String name) {
        return swapiPort.findStarshipByName(name);
    }
}
