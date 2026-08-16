package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiPeopleListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
public class SwapiMapper {
    public People toPeople(SwapiResult<SwapiPeopleProperties> result){
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

    public People toPeopleList(SwapiPeopleListItem result){

        return new People(
                Long.valueOf(result.uid()),
                result.name(),
                result.url()
        );
    }


    public Film toFilmsList(SwapiResult<SwapiFilmProperties> result){

        SwapiFilmProperties properties = result.properties();

        return new Film(
                Long.valueOf(result.uid()),
                properties.title(),
                properties.episode_id(),
                properties.opening_crawl(),
                properties.director(),
                properties.producer(),
                LocalDate.parse(properties.release_date()),
                properties.species(),
                properties.starships(),
                properties.vehicles(),
                properties.characters(),
                properties.planets(),
                properties.url(),
                Instant.parse(properties.created()),
                Instant.parse(properties.edited())
        );
    }

}
