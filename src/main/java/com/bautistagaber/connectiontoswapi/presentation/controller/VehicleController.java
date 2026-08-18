package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.VehicleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.ListResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.VehicleResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.VehicleResponseMapper;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleResponseMapper vehicleResponseMapper;

    public VehicleController(VehicleService vehicleService, VehicleResponseMapper vehicleResponseMapper) {
        this.vehicleService = vehicleService;
        this.vehicleResponseMapper = vehicleResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ListResponse>> findVehicles(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        PageResult<Vehicle> result = vehicleService.findVehicles(page, size);

        List<ListResponse> vehicles = result.content().stream().map(vehicle -> ListResponse.builder().id(vehicle.getId()).name(vehicle.getName()).url(vehicle.getUrl()).build()).toList();

        PageResponse<ListResponse> response = PageResponse.<ListResponse>builder().content(vehicles).page(result.page()).size(result.size()).totalElements(result.totalElements()).totalPages(result.totalPages()).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> findPersonById(@PathVariable @Positive Long id) {

        return vehicleService.findVehicleById(id)
                .map(vehicleResponseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<VehicleResponse>> findPersonByName(@RequestParam @NotBlank String name) {
        List<VehicleResponse> vehicle = vehicleService.findVehicleByName(name)
                .stream()
                .map(vehicleResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(vehicle);
    }

}
