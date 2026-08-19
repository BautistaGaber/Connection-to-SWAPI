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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwapiMapperTest {

    private SwapiMapper swapiMapper;

    @BeforeEach
    void setUp() {
        swapiMapper = new SwapiMapper();
    }

    @Test
    void toPeople_mapsCorrectly() {
        SwapiPeopleProperties properties = new SwapiPeopleProperties(
                "Luke Skywalker", "19BBY", "blue", List.of("https://swapi.tech/api/films/1"),
                "male", "blond", "172", "https://swapi.tech/api/planets/1", "77", "fair",
                Instant.parse("2014-12-09T13:50:51.644000Z"),
                Instant.parse("2014-12-20T21:17:56.897000Z"),
                List.of("https://swapi.tech/api/species/1"),
                List.of("https://swapi.tech/api/starships/12"),
                "https://swapi.tech/api/people/1",
                List.of("https://swapi.tech/api/vehicles/14")
        );
        SwapiResult<SwapiPeopleProperties> result = new SwapiResult<>("1", "A person", properties);

        People people = swapiMapper.toPeople(result);

        assertEquals(1L, people.getId());
        assertEquals("Luke Skywalker", people.getName());
        assertEquals("19BBY", people.getBirthYear());
        assertEquals("blue", people.getEyeColor());
        assertEquals("male", people.getGender());
    }

    @Test
    void toPeopleList_mapsCorrectly() {
        SwapiListItem item = new SwapiListItem("1", "Luke Skywalker", "https://swapi.tech/api/people/1");

        People people = swapiMapper.toPeopleList(item);

        assertEquals(1L, people.getId());
        assertEquals("Luke Skywalker", people.getName());
        assertEquals("https://swapi.tech/api/people/1", people.getUrl());
    }

    @Test
    void toFilm_mapsCorrectly() {
        SwapiFilmProperties properties = new SwapiFilmProperties(
                "2014-12-10T14:23:25.716000Z", "2014-12-20T19:49:45.256000Z",
                List.of("https://swapi.tech/api/starships/12"),
                List.of("https://swapi.tech/api/vehicles/14"),
                List.of("https://swapi.tech/api/planets/1"),
                "Gary Kurtz", "A New Hope", 4, "George Lucas", "1977-05-25",
                "It is a period of civil war...",
                List.of("https://swapi.tech/api/people/1"),
                List.of("https://swapi.tech/api/species/1"),
                "https://swapi.tech/api/films/1"
        );
        SwapiResult<SwapiFilmProperties> result = new SwapiResult<>("1", "A film", properties);

        Film film = swapiMapper.toFilm(result);

        assertEquals(1L, film.getId());
        assertEquals("A New Hope", film.getTitle());
        assertEquals(4, film.getEpisodeId());
        assertEquals("George Lucas", film.getDirector());
    }

    @Test
    void toStarship_mapsCorrectly() {
        SwapiStarshipProperties properties = new SwapiStarshipProperties(
                "2014-12-10T14:20:33.369000Z", "2014-12-20T19:49:45.256000Z",
                "1 month", "Millennium Falcon", "100000", "6", "1050",
                "4", "34.37", "SF-1000", "1000000", "Corellian Engineering Corporation",
                List.of("https://swapi.tech/api/people/1"), "0.5",
                "Deep-Space Frigate", "Class 1.0",
                List.of("https://swapi.tech/api/films/1"), "https://swapi.tech/api/starships/10"
        );
        SwapiResult<SwapiStarshipProperties> result = new SwapiResult<>("10", "A starship", properties);

        Starship starship = swapiMapper.toStarship(result);

        assertEquals(10L, starship.getId());
        assertEquals("Millennium Falcon", starship.getName());
        assertEquals("Corellian Engineering Corporation", starship.getManufacturer());
    }

    @Test
    void toStarshipList_mapsCorrectly() {
        SwapiListItem item = new SwapiListItem("10", "Millennium Falcon", "https://swapi.tech/api/starships/10");

        Starship starship = swapiMapper.toStarshipList(item);

        assertEquals(10L, starship.getId());
        assertEquals("Millennium Falcon", starship.getName());
        assertEquals("https://swapi.tech/api/starships/10", starship.getUrl());
    }

    @Test
    void toVehicle_mapsCorrectly() {
        SwapiVehicleProperties properties = new SwapiVehicleProperties(
                "2014-12-10T15:36:25.724000Z", "2014-12-20T21:30:21.548000Z",
                "2 months", "Sand Crawler", "200000", "30", "30",
                "4", "36.8", "Digger Crawler", "150000", "Corellia Mining Corporation",
                "wheeled", List.of(), List.of("https://swapi.tech/api/films/1"),
                "https://swapi.tech/api/vehicles/4"
        );
        SwapiResult<SwapiVehicleProperties> result = new SwapiResult<>("4", "A vehicle", properties);

        Vehicle vehicle = swapiMapper.toVehicle(result);

        assertEquals(4L, vehicle.getId());
        assertEquals("Sand Crawler", vehicle.getName());
        assertEquals("wheeled", vehicle.getVehicleClass());
    }

    @Test
    void toVehiclesList_mapsCorrectly() {
        SwapiListItem item = new SwapiListItem("4", "Sand Crawler", "https://swapi.tech/api/vehicles/4");

        Vehicle vehicle = swapiMapper.toVehiclesList(item);

        assertEquals(4L, vehicle.getId());
        assertEquals("Sand Crawler", vehicle.getName());
        assertEquals("https://swapi.tech/api/vehicles/4", vehicle.getUrl());
    }
}
