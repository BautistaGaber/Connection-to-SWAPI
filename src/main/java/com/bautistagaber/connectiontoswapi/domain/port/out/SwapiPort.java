package com.bautistagaber.connectiontoswapi.domain.port.out;

import com.bautistagaber.connectiontoswapi.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface SwapiPort {

    PageResult<People> findPeople(int page, int size, String name);

    Optional<People> findPersonById(Long id);

    PageResult<Film> findFilm(int page, int size, String name);

    Optional<Film> findFilmById(Long id);

    PageResult<Starship> findStarships(int page, int size, String name);

    Optional<Starship> findStarshipById(Long id);

    PageResult<Vehicle> findVehicles(int page, int size, String name);

    Optional<Vehicle> findVehicleById(Long id);
}
