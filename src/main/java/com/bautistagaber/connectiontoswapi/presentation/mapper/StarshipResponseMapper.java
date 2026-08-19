package com.bautistagaber.connectiontoswapi.presentation.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.StarshipResponse;
import org.springframework.stereotype.Component;

/**
 * Mapper that converts the domain Starship model into the StarshipResponse DTO.
 */
@Component
public class StarshipResponseMapper {

    public StarshipResponse toResponse(Starship starship){
        return StarshipResponse.builder()
                .id(starship.getId())
                .name(starship.getName())
                .model(starship.getModel())
                .manufacturer(starship.getManufacturer())
                .costInCredits(starship.getCostInCredits())
                .length(starship.getLength())
                .maxAtmospheringSpeed(starship.getMaxAtmospheringSpeed())
                .crew(starship.getCrew())
                .passengers(starship.getPassengers())
                .cargoCapacity(starship.getCargoCapacity())
                .consumables(starship.getConsumables())
                .hyperdriveRating(starship.getHyperdriveRating())
                .MGLT(starship.getMglt())
                .starshipClass(starship.getStarshipClass())
                .pilots(starship.getPilots())
                .films(starship.getFilms())
                .url(starship.getUrl())
                .created(starship.getCreated())
                .edited(starship.getEdited())
                .build();
    }
}
