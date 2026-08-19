package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private SwapiPort swapiPort;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    void findVehicles_withName_returnsResults() {
        Vehicle vehicle = new Vehicle(1L, "Sand Crawler", null, null, null, null, null,
                null, null, null, null, null, null, null,
                "https://swapi.tech/api/vehicles/4", null, null);
        PageResult<Vehicle> pageResult = new PageResult<>(List.of(vehicle), 0, 10, 1, 1);

        when(swapiPort.findVehicleByName("Crawler", 0, 10)).thenReturn(pageResult);

        PageResult<Vehicle> result = vehicleService.findVehicles(0, 10, "Crawler");

        assertEquals(1, result.content().size());
        assertEquals("Sand Crawler", result.content().getFirst().getName());
    }

    @Test
    void findVehicles_withNameEmpty_throwsResourceNotFoundException() {
        PageResult<Vehicle> emptyResult = new PageResult<>(List.of(), 0, 10, 0, 0);

        when(swapiPort.findVehicleByName("Invento", 0, 10)).thenReturn(emptyResult);

        assertThrows(ResourceNotFoundException.class,
                () -> vehicleService.findVehicles(0, 10, "Invento"));
    }

    @Test
    void findVehicles_withoutName_returnsPaginatedResults() {
        Vehicle vehicle = new Vehicle(1L, "Sand Crawler", null, null, null, null, null,
                null, null, null, null, null, null, null,
                "https://swapi.tech/api/vehicles/4", null, null);
        PageResult<Vehicle> pageResult = new PageResult<>(List.of(vehicle), 0, 10, 39, 4);

        when(swapiPort.findVehicles(0, 10)).thenReturn(pageResult);

        PageResult<Vehicle> result = vehicleService.findVehicles(0, 10, null);

        assertEquals(1, result.content().size());
        assertEquals(39, result.totalElements());
    }

    @Test
    void findVehicleById_returnsVehicle() {
        Vehicle vehicle = new Vehicle(1L, "Sand Crawler", null, null, null, null, null,
                null, null, null, null, null, null, null,
                "https://swapi.tech/api/vehicles/4", null, null);

        when(swapiPort.findVehicleById(1L)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> result = vehicleService.findVehicleById(1L);

        assertTrue(result.isPresent());
        assertEquals("Sand Crawler", result.get().getName());
    }

    @Test
    void findVehicleById_returnsEmpty_whenNotFound() {
        when(swapiPort.findVehicleById(999L)).thenReturn(Optional.empty());

        Optional<Vehicle> result = vehicleService.findVehicleById(999L);

        assertTrue(result.isEmpty());
    }
}
