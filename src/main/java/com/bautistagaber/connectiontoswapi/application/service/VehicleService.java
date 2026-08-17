package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    PageResult<Vehicle> findVehicles(int page, int size);

    Optional<Vehicle> findVehicleById(Long id);

    List<Vehicle> findVehicleByName(String name);
}
