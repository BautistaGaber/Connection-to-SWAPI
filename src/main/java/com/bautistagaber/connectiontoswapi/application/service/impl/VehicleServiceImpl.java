package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.VehicleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final SwapiPort swapiPort;

    public VehicleServiceImpl(SwapiPort swapiPort){
        this.swapiPort= swapiPort;
    }

    public PageResult<Vehicle> findVehicles(int page, int size, String name){

        if (name == null || name.isBlank()) {
            return swapiPort.findVehicles(page, size);
        }

        PageResult<Vehicle> result = swapiPort.findVehicleByName(name, page, size);

        if (result.content().isEmpty()) {
            throw new ResourceNotFoundException("Vehicle", name);
        }

        return result;
    }

    public Optional<Vehicle> findVehicleById(Long id){
        return swapiPort.findVehicleById(id);
    }
}
