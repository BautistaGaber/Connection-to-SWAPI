package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;

import java.util.Optional;

public class PeopleServiceImpl implements PeopleService {

    private final SwapiPort swapiPort;

    public PeopleServiceImpl(SwapiPort swapiPort) {
        this.swapiPort = swapiPort;
    }

    @Override
    public PageResult<People> findPeople(int page, int size, String name) {
        return swapiPort.findPeople(page, size, name);
    }

    @Override
    public Optional<People> findPersonById(Long id) {
        return swapiPort.findPersonById(id);
    }
}
