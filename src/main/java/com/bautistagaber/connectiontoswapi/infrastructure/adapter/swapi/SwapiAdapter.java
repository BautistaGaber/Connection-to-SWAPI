package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi;

import com.bautistagaber.connectiontoswapi.domain.model.*;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client.SwapiClient;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper.SwapiMapper;

import java.util.List;
import java.util.Optional;

public class SwapiAdapter implements SwapiPort {

    private final SwapiClient swapiClient;
    private final SwapiMapper swapiMapper;

    public SwapiAdapter(SwapiClient swapiClient, SwapiMapper swapiMapper) {
        this.swapiClient = swapiClient;
        this.swapiMapper = swapiMapper;
    }

    @Override
    public PageResult<People> findPeople(int page, int size, String name) {

        int swapiPage = page + 1;

        SwapiListResponse<SwapiResult<SwapiPeopleProperties>> response = swapiClient.getPeople(swapiPage, size, name);

        List<People> people = response.results().stream().map(swapiMapper::toPeople).toList();

        return new PageResult<>(people, page, size, response.totalRecords(), response.totalPages());
    }

    @Override
    public Optional<People> findPersonById(Long id) {
        return Optional.ofNullable(swapiClient.getPersonById(id)).map(response -> swapiMapper.toPeople(response.result()));
    }

    @Override
    public PageResult<Film> findFilm(int page, int size, String name) {
        return null;
    }

    @Override
    public Optional<Film> findFilmById(Long id) {
        return Optional.empty();
    }

    @Override
    public PageResult<Starship> findStarships(int page, int size, String name) {
        return null;
    }

    @Override
    public Optional<Starship> findStarshipById(Long id) {
        return Optional.empty();
    }

    @Override
    public PageResult<Vehicle> findVehicles(int page, int size, String name) {
        return null;
    }

    @Override
    public Optional<Vehicle> findVehicleById(Long id) {
        return Optional.empty();
    }
}
