package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.service.VehicleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final SwapiPort swapiPort;

    public VehicleServiceImpl(SwapiPort swapiPort){
        this.swapiPort= swapiPort;
    }

    public PageResult<Vehicle> findVehicles(int page, int size){
        return swapiPort.findVehicles(page, size);
    }

    public Optional<Vehicle> findVehicleById(Long id){
        return swapiPort.findVehicleById(id);
    }

    public List<Vehicle> findVehicleByName(String name){
        return swapiPort.findVehicleByName(name);
    }
}
