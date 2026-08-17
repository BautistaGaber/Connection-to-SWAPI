package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client;

import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.*;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SwapiClient {

    private final WebClient webClient;

    public SwapiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public SwapiListResultsResponse<SwapiListItem> getPeople(int page, int limit) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build()
                )
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiListResultsResponse<SwapiListItem>
                                >() {}
                )
                .block();

    }

    public SwapiResponse<SwapiPeopleProperties> getPersonById(Long id) {

        return webClient.get()
                .uri("/people/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiPeopleProperties>>() {
        })
                .block();
    }

    public SwapiListResultResponse<SwapiPeopleProperties> getPersonByName(String name) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/")
                        .queryParam("name", name)
                        .build()
                )
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiListResultResponse<SwapiPeopleProperties>
                                >() {}
                )
                .block();
    }

    public SwapiListResultResponse<SwapiFilmProperties> getFilms(int page, int limit) {

        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/films/")
                                .queryParam("page", page)
                                .queryParam("limit", limit)
                                .build()
                        )
                        .retrieve()
                        .bodyToMono(
                                new ParameterizedTypeReference<
                                        SwapiListResultResponse<SwapiFilmProperties>
                                        >() {
                                }
                        )
                        .block();
    }

    public SwapiResponse<SwapiFilmProperties> getFilmsById(Long id){
        return webClient.get()
                .uri("/films/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiFilmProperties>>() {
                })
                .block();
    }

    public SwapiListResultResponse<SwapiFilmProperties> getFilmsByName(String name){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/films/")
                        .queryParam("title", name)
                        .build())
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiListResultResponse<SwapiFilmProperties>
                                >() {}
                )
                .block();
    }

    public SwapiListResultsResponse<SwapiListItem> getStarships(int page, int limit) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/starships/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build()
                )
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiListResultsResponse<SwapiListItem>
                                >() {}
                )
                .block();

    }

    public SwapiResponse<SwapiStarshipProperties> getStarshipsById(Long id){
        return webClient.get()
                .uri("/starships/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiStarshipProperties>>() {
                })
                .block();
    }

    public SwapiListResultResponse<SwapiStarshipProperties> getStarshipsByName(String name){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/Starships/")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiListResultResponse<SwapiStarshipProperties>>() {}
                )
                .block();
    }

}
