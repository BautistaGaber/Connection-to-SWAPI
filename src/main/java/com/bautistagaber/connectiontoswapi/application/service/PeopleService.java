package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;

import java.util.List;
import java.util.Optional;

public interface PeopleService {

    PageResult<People> findPeople(int page, int size);

    Optional<People> findPersonById(Long id);

    List<People> findPersonByName(String name);

}
