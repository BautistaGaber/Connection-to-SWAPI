package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client;

import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiListResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiResult;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.SwapiSearchResponse;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiPeopleListItem;
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

    public SwapiListResponse<SwapiPeopleListItem> getPeople(int page, int limit) {

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
                                SwapiListResponse<SwapiPeopleListItem>
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

}
