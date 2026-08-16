package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiPeopleListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import org.springframework.stereotype.Component;

@Component
public class SwapiMapper {
    public People toPeople(
            SwapiResult<SwapiPeopleProperties> result
    ){
        SwapiPeopleProperties properties = result.properties();

        return new People(
                Long.valueOf(result.uid()),
                properties.name(),
                properties.birthYear(),
                properties.eyeColor(),
                properties.films(),
                properties.gender(),
                properties.hairColor(),
                properties.height(),
                properties.homeworld(),
                properties.mass(),
                properties.skinColor(),
                properties.created(),
                properties.edited(),
                properties.species(),
                properties.starships(),
                properties.url(),
                properties.vehicles()
        );

    }

    public People toPeopleList(
            SwapiPeopleListItem result
    ){

        return new People(
                Long.valueOf(result.uid()),
                result.name(),
                result.url()
        );
    }

}
