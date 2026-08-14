package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.mapper;

import com.bautistagaber.connectiontoswapi.domain.model.Film;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.domain.model.Vehicle;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiVehicleProperties;

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
    public Film toFilm(
            SwapiResult<SwapiFilmProperties> result
    ) {
        SwapiFilmProperties properties = result.properties();

        return new Film(
                Long.valueOf(result.uid()),
                properties.title(),
                properties.episodeId(),
                properties.openingCrawl(),
                properties.director(),
                properties.producer(),
                properties.releaseDate(),
                properties.species(),
                properties.starships(),
                properties.vehicles(),
                properties.characters(),
                properties.planets(),
                properties.url(),
                properties.created(),
                properties.edited()
        );
    }

    public Starship toStarship(
            SwapiResult<SwapiStarshipProperties> result
    ) {
        SwapiStarshipProperties properties = result.properties();

        return new Starship(
                Long.valueOf(result.uid()),
                properties.name(),
                properties.model(),
                properties.starshipClass(),
                properties.manufacturer(),
                properties.costInCredits(),
                properties.length(),
                properties.crew(),
                properties.passengers(),
                properties.maxAtmospheringSpeed(),
                properties.hyperdriveRating(),
                properties.mglt(),
                properties.cargoCapacity(),
                properties.consumables(),
                properties.films(),
                properties.pilots(),
                properties.url(),
                properties.created(),
                properties.edited()
        );
    }

    public Vehicle toVehicle(
            SwapiResult<SwapiVehicleProperties> result
    ) {
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
                properties.created(),
                properties.edited()
        );
    }
}
