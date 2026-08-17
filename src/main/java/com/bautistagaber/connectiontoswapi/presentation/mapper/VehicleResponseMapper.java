package com.bautistagaber.connectiontoswapi.presentation.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleResponseMapper {

    public VehicleResponse toResponse(Vehicle vehicle){
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .name(vehicle.getName())
                .model(vehicle.getModel())
                .manufacturer(vehicle.getManufacturer())
                .costInCredits(vehicle.getCostInCredits())
                .length(vehicle.getLength())
                .maxAtmospheringSpeed(vehicle.getMaxAtmospheringSpeed())
                .crew(vehicle.getCrew())
                .passengers(vehicle.getPassengers())
                .cargoCapacity(vehicle.getCargoCapacity())
                .consumables(vehicle.getConsumables())
                .films(vehicle.getFilms())
                .pilots(vehicle.getPilots())
                .url(vehicle.getUrl())
                .created(vehicle.getCreated())
                .edited(vehicle.getEdited())
                .build();

    }
}
