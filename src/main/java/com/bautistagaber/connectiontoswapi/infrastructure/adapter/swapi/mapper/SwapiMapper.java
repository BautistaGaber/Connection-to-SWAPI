package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiVehicleProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Mapper that converts SWAPI infrastructure DTOs into domain models
 * (People, Film, Starship, Vehicle). Handles type conversions such as String uid to Long.
 */
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
                properties.starship_class(),
                properties.manufacturer(),
                properties.cost_in_credits(),
                properties.length(),
                properties.crew(),
                properties.passengers(),
                properties.max_atmosphering_speed(),
                properties.hyperdrive_rating(),
                properties.MGLT(),
                properties.cargo_capacity(),
                properties.consumables(),
                properties.films(),
                properties.pilots(),
                properties.url(),
                Instant.parse(properties.created()),
                Instant.parse(properties.edited())
        );
    }

    public Vehicle toVehiclesList(SwapiListItem result){

        return new Vehicle(
                Long.valueOf(result.uid()),
                result.name(),
                result.url()
        );
    }

    public Vehicle toVehicle(SwapiResult<SwapiVehicleProperties> result) {

        SwapiVehicleProperties properties = result.properties();

        return new Vehicle(
                Long.valueOf(result.uid()),
                properties.name(),
                properties.model(),
                properties.vehicleClass(),
                properties.manufacturer(),
                properties.length(),
                properties.costInCredits(),
                properties.crew(),
                properties.passengers(),
                properties.maxAtmospheringSpeed(),
                properties.cargoCapacity(),
                properties.consumables(),
                properties.films(),
                properties.pilots(),
                properties.url(),
                Instant.parse(properties.created()),
                Instant.parse(properties.edited())
        );
    }
}
