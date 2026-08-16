package com.bautistagaber.connectiontoswapi.domain.port.out;

import com.bautistagaber.connectiontoswapi.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface SwapiPort {

    PageResult<People> findPeople(int page, int size);

    Optional<People> findPersonById(Long id);

    List<People> findPersonByName(String name);
    
    PageResult<Film> findFilms(int page, int size);
}
