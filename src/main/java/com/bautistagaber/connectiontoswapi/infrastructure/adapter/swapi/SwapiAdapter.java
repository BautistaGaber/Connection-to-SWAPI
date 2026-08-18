package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi;

import com.bautistagaber.connectiontoswapi.domain.model.*;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client.SwapiClient;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResultResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResultsResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiVehicleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper.SwapiMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SwapiAdapter implements SwapiPort {

    private final SwapiClient swapiClient;
    private final SwapiMapper swapiMapper;

    public SwapiAdapter(SwapiClient swapiClient, SwapiMapper swapiMapper) {
        this.swapiClient = swapiClient;
        this.swapiMapper = swapiMapper;
    }

    @Override
    public PageResult<People> findPeople(int page, int size) {

        int swapiPage = page + 1;

        SwapiListResultsResponse<SwapiListItem> response =
                swapiClient.getPeople(swapiPage, size);

        List<People> people = response.results()
                .stream()
                .map(swapiMapper::toPeopleList)
                .toList();

        return new PageResult<>(
                people,
                page,
                size,
                response.totalRecords(),
                response.totalPages()
        );
    }

    @Override
    public Optional<People> findPersonById(Long id) {
        return Optional.ofNullable(swapiClient.getPersonById(id))
                .map(response -> swapiMapper.toPeople(response.result()));
    }

    @Override
    public PageResult<People> findPersonByName(String name, int page, int size) {

        SwapiListResultResponse<SwapiPeopleProperties> response =
                swapiClient.getPersonByName(name);

        List<People> people = response.result()
                .stream()
                .map(swapiMapper::toPeople)
                .toList();

        return new PageResult<>(
                people,
                page,
                size,
                people.size(),
                1
        );
    }

    @Override
    public PageResult<Film> findFilms(int page, int size) {
        int swapiPage = page + 1;

        SwapiListResultResponse<SwapiFilmProperties> response =
                swapiClient.getFilms(swapiPage, size);

        List<Film> films = response.result()
                .stream()
                .map(swapiMapper::toFilm)
                .toList();

        //logica que trae todos los datos de la api para realizar la paginacion
        int totalElements = films.size();

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        List<Film> paginatedFilms = fromIndex < totalElements
                ? films.subList(fromIndex, toIndex)
                : List.of();

        int totalPages = (int) Math.ceil(
                (double) totalElements / size
        );

        return new PageResult<>(
                paginatedFilms,
                page,
                size,
                totalElements,
                totalPages
        );
    }

    @Override
    public Optional<Film> findFilmById(Long id){
        return Optional.ofNullable(swapiClient.getFilmsById(id))
                .map(response -> swapiMapper.toFilm(response.result()));
    }

    @Override
    public PageResult<Film> findFilmByName(String name, int page, int size) {
        SwapiListResultResponse<SwapiFilmProperties> response =
                swapiClient.getFilmsByName(name);

        List<Film> films = response.result()
                .stream()
                .map(swapiMapper::toFilm)
                .toList();

        return new PageResult<>(
                films,
                page,
                size,
                films.size(),
                1
        );
    }

    @Override
    public PageResult<Starship> findStarships(int page, int size) {

        int swapiPage = page + 1;

        SwapiListResultsResponse<SwapiListItem> response =
                swapiClient.getStarships(swapiPage, size);

        List<Starship> Starship = response.results()
                .stream()
                .map(swapiMapper::toStarshipList)
                .toList();

        return new PageResult<>(
                Starship,
                page,
                size,
                response.totalRecords(),
                response.totalPages()
        );
    }

    @Override
    public Optional<Starship> findStarshipById(Long id){
        return Optional.ofNullable(swapiClient.getStarshipsById(id))
                .map(response -> swapiMapper.toStarship(response.result()));
    }

    @Override
    public PageResult<Starship> findStarshipByName(String name, int page, int size) {
        SwapiListResultResponse<SwapiStarshipProperties> response =
                swapiClient.getStarshipsByName(name);

        List<Starship> Starships = response.result()
                .stream()
                .map(swapiMapper::toStarship)
                .toList();

        return new PageResult<>(
                Starships,
                page,
                size,
                Starships.size(),
                1
        );
    }

    @Override
    public PageResult<Vehicle> findVehicles(int page, int size) {

        int swapiPage = page + 1;

        SwapiListResultsResponse<SwapiListItem> response =
                swapiClient.getVehicles(swapiPage, size);

        List<Vehicle> vehicle = response.results()
                .stream()
                .map(swapiMapper::toVehiclesList)
                .toList();

        return new PageResult<>(
                vehicle,
                page,
                size,
                response.totalRecords(),
                response.totalPages()
        );
    }

    @Override
    public Optional<Vehicle> findVehicleById(Long id){
        return Optional.ofNullable(swapiClient.getVehicleById(id))
                .map(response -> swapiMapper.toVehicle(response.result()));
    }

    @Override
    public PageResult<Vehicle> findVehicleByName(String name, int page, int size) {
        SwapiListResultResponse<SwapiVehicleProperties> response =
                swapiClient.getVehicleByName(name);

        List<Vehicle> vehicle = response.result()
                .stream()
                .map(swapiMapper::toVehicle)
                .toList();

        return new PageResult<>(
                vehicle,
                page,
                size,
                vehicle.size(),
                1
        );
    }


}
