package com.bautistagaber.connectiontoswapi.domain.port.out;

import com.bautistagaber.connectiontoswapi.domain.model.*;

import java.util.Optional;

/**
 * Outbound port defining all SWAPI data access operations.
 * Implemented by SwapiAdapter in the infrastructure layer.
 */
public interface SwapiPort {

    PageResult<People> findPeople(int page, int size);

    Optional<People> findPersonById(Long id);

    PageResult<People> findPersonByName(String name, int page, int size);
    
    PageResult<Film> findFilms(int page, int size);

    Optional<Film> findFilmById(Long id);

    PageResult<Film> findFilmByName(String name, int page, int size);

    PageResult<Starship> findStarships(int page, int size);

    Optional<Starship> findStarshipById(Long id);

    PageResult<Starship> findStarshipByName(String name, int page, int size);

    PageResult<Vehicle> findVehicles(int page, int size);

    Optional<Vehicle> findVehicleById(Long id);

    PageResult<Vehicle> findVehicleByName(String name, int page, int size);
}
