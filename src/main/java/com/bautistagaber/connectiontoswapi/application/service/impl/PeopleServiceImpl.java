package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the people/characters service.
 * Delegates queries to SwapiPort and validates that name searches return results.
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    private final SwapiPort swapiPort;

    public PeopleServiceImpl(SwapiPort swapiPort) {
        this.swapiPort = swapiPort;
    }

    @Override
    public PageResult<People> findPeople(int page, int size, String name) {
        if (name == null || name.isBlank()) {
            return swapiPort.findPeople(page, size);
        }

        PageResult<People> result = swapiPort.findPersonByName(name, page, size);

        if (result.content().isEmpty()) {
            throw new ResourceNotFoundException("Person", name);
        }

        return result;
    }

    @Override
    public Optional<People> findPersonById(Long id) {
        return swapiPort.findPersonById(id);
    }
}
