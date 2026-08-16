package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client;

import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.*;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiPeopleListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
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

    public SwapiListResultsResponse<SwapiPeopleListItem> getPeople(int page, int limit) {

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
                                SwapiListResultsResponse<SwapiPeopleListItem>
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

    public List<SwapiResult<SwapiPeopleProperties>> getPersonByName(String name) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/")
                        .queryParam("name", name)
                        .build()
                )
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<
                                SwapiSearchResponse<SwapiPeopleProperties>
                                >() {}
                )
                .map(SwapiSearchResponse::result)
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
}
