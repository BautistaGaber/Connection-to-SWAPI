package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;

import java.util.Optional;

/**
 * Service interface for people/characters: paginated queries and search by ID.
 */
public interface PeopleService {

    PageResult<People> findPeople(int page, int size, String name);

    Optional<People> findPersonById(Long id);

}
