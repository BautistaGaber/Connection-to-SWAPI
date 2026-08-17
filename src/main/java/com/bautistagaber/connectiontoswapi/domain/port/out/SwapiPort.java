package com.bautistagaber.connectiontoswapi.domain.port.out;

import com.bautistagaber.connectiontoswapi.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface SwapiPort {

    PageResult<People> findPeople(int page, int size);

    Optional<People> findPersonById(Long id);

    List<People> findPersonByName(String name);
    
    PageResult<Film> findFilms(int page, int size);

    Optional<Film> findFilmById(Long id);

    List<Film> findFilmByName(String name);

    PageResult<Starship> findStarships(int page, int size);

    Optional<Starship> findStarshipById(Long id);

    List<Starship> findStarshipByName(String name);
}
