package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi;

import com.bautistagaber.connectiontoswapi.domain.model.*;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client.SwapiClient;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResultResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResultsResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
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
    public List<People> findPersonByName(String name) {
        SwapiListResultResponse<SwapiPeopleProperties> response =
                swapiClient.getPersonByName(name);

        return response.result()
                .stream()
                .map(swapiMapper::toPeople)
                .toList();
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
    public List<Film> findFilmByName(String name) {
        SwapiListResultResponse<SwapiFilmProperties> response =
                swapiClient.getFilmsByName(name);

        return response.result()
                .stream()
                .map(swapiMapper::toFilm)
                .toList();
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

    public Optional<Starship> findStarshipById(Long id){
        return Optional.ofNullable(swapiClient.getStarshipsById(id))
                .map(response -> swapiMapper.toStarship(response.result()));
    }

    @Override
    public List<Starship> findStarshipByName(String name) {
        SwapiListResultResponse<SwapiStarshipProperties> response =
                swapiClient.getStarshipsByName(name);

        return response.result()
                .stream()
                .map(swapiMapper::toStarship)
                .toList();
    }
}
