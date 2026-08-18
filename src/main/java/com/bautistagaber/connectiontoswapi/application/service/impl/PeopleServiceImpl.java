package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return swapiPort.findPersonByName(name, page, size);
    }

    @Override
    public Optional<People> findPersonById(Long id) {
        return swapiPort.findPersonById(id);
    }
}
