package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client;

import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

public class SwapiClient {
    private final WebClient webClient;


    public SwapiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public SwapiListResponse<SwapiResult<SwapiPeopleProperties>> getPeople(int page, int limit, String name) {

        return webClient.get().uri(uriBuilder -> uriBuilder
                .path("/people/")
                .queryParam("page", page)
                .queryParam("limit", limit)
                .queryParamIfPresent("name",
                        java.util.Optional.ofNullable(name))
                .build()
        )
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<SwapiListResponse<SwapiResult<SwapiPeopleProperties>>>() {
        }).block();
    }

    public SwapiResponse<SwapiPeopleProperties> getPersonById(Long id) {

        return webClient.get()
                .uri("/people/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiPeopleProperties>>() {
        })
                .block();
    }
}
