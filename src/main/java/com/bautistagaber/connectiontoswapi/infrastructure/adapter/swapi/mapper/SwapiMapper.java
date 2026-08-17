package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
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

    public People toPeopleList(SwapiListItem result){

        return new People(
                Long.valueOf(result.uid()),
                result.name(),
                result.url()
        );
    }


    public Film toFilm(SwapiResult<SwapiFilmProperties> result){

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


    public Starship toStarshipList(SwapiListItem result){

        return new Starship(
                Long.valueOf(result.uid()),
                result.name(),
                result.url()
        );
    }

    public Starship toStarship(SwapiResult<SwapiStarshipProperties> result) {

        SwapiStarshipProperties properties = result.properties();

        return new Starship(
                Long.valueOf(result.uid()),
                properties.name(),
                properties.model(),
                properties.manufacturer(),
                properties.cost_in_credits(),
                properties.length(),
                properties.max_atmosphering_speed(),
                properties.crew(),
                properties.passengers(),
                properties.cargo_capacity(),
                properties.consumables(),
                properties.hyperdrive_rating(),
                properties.MGLT(),
                properties.starship_class(),
                properties.pilots(),
                properties.films(),
                properties.url(),
                Instant.parse(properties.created()),
                Instant.parse(properties.edited())
        );
    }
}
