package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;

import java.util.Optional;

/**
 * Service interface for vehicles: paginated queries and search by ID.
 */
public interface VehicleService {
    PageResult<Vehicle> findVehicles(int page, int size, String name);

    Optional<Vehicle> findVehicleById(Long id);
}
