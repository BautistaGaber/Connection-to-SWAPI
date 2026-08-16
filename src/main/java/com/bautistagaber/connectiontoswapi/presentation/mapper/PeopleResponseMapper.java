package com.bautistagaber.connectiontoswapi.presentation.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import org.springframework.stereotype.Component;

@Component
public class PeopleResponseMapper {

    public PeopleResponse toResponse(People people){
        return PeopleResponse.builder()
                .id(people.getId())
                .name(people.getName())
                .birthYear(people.getBirthYear())
                .eyeColor(people.getEyeColor())
                .films(people.getFilms())
                .gender(people.getGender())
                .hairColor(people.getHairColor())
                .height(people.getHeight())
                .homeworld(people.getHomeworld())
                .mass(people.getMass())
                .skinColor(people.getSkinColor())
                .created(people.getCreated())
                .edited(people.getEdited())
                .species(people.getSpecies())
                .starships(people.getStarships())
                .url(people.getUrl())
                .vehicles(people.getVehicles())
                .build();
    }
}
