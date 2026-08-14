package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;

import java.util.Optional;

public interface PeopleService {

    PageResult<People> findPeople(int page, int size, String name);

    Optional<People> findPersonById(Long id);

}
