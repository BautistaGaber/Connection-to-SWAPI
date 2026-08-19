package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.client;

import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.*;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list.SwapiListItem;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiFilmProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiPeopleProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiStarshipProperties;
import com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties.SwapiVehicleProperties;
import com.bautistagaber.connectiontoswapi.presentation.exception.SwapiApiException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.function.Supplier;

/**
 * Low-level HTTP client that consumes the external SWAPI API using WebClient.
 * Handles connection errors and non-successful responses by wrapping them in SwapiApiException.
 */
@Component
public class SwapiClient {

    private final WebClient webClient;

    public SwapiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public SwapiListResultsResponse<SwapiListItem> getPeople(int page, int limit) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultsResponse<SwapiListItem>>() {})
                .block(), "people");
    }

    public SwapiResponse<SwapiPeopleProperties> getPersonById(Long id) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri("/people/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiPeopleProperties>>() {})
                .block(), "person with id " + id);
    }

    public SwapiListResultResponse<SwapiPeopleProperties> getPersonByName(String name) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultResponse<SwapiPeopleProperties>>() {})
                .block(), "people by name");
    }

    public SwapiListResultResponse<SwapiFilmProperties> getFilms(int page, int limit) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/films/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultResponse<SwapiFilmProperties>>() {})
                .block(), "films");
    }

    public SwapiResponse<SwapiFilmProperties> getFilmsById(Long id) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri("/films/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiFilmProperties>>() {})
                .block(), "film with id " + id);
    }

    public SwapiListResultResponse<SwapiFilmProperties> getFilmsByName(String name) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/films/")
                        .queryParam("title", name)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultResponse<SwapiFilmProperties>>() {})
                .block(), "films by name");
    }

    public SwapiListResultsResponse<SwapiListItem> getStarships(int page, int limit) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/starships/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultsResponse<SwapiListItem>>() {})
                .block(), "starships");
    }

    public SwapiResponse<SwapiStarshipProperties> getStarshipsById(Long id) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri("/starships/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiStarshipProperties>>() {})
                .block(), "starship with id " + id);
    }

    public SwapiListResultResponse<SwapiStarshipProperties> getStarshipsByName(String name) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/Starships/")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultResponse<SwapiStarshipProperties>>() {})
                .block(), "starships by name");
    }

    public SwapiListResultsResponse<SwapiListItem> getVehicles(int page, int limit) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vehicles/")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultsResponse<SwapiListItem>>() {})
                .block(), "vehicles");
    }

    public SwapiResponse<SwapiVehicleProperties> getVehicleById(Long id) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri("/vehicles/{id}/", id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiResponse<SwapiVehicleProperties>>() {})
                .block(), "vehicle with id " + id);
    }

    public SwapiListResultResponse<SwapiVehicleProperties> getVehicleByName(String name) {
        return executeWithExceptionHandling(() -> webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/vehicles/")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SwapiListResultResponse<SwapiVehicleProperties>>() {})
                .block(), "vehicles by name");
    }

    private <T> T executeWithExceptionHandling(Supplier<T> operation, String resource) {
        try {
            return operation.get();
        } catch (WebClientResponseException e) {
            throw new SwapiApiException("SWAPI returned error " + e.getStatusCode() + " while fetching " + resource, e);
        } catch (Exception e) {
            throw new SwapiApiException("Failed to connect to SWAPI while fetching " + resource, e);
        }
    }
}
