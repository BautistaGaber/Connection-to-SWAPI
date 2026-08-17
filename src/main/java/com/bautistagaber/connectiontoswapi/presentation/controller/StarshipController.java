package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.StarshipService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.ListResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.StarshipResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.StarshipResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starships")
public class StarshipController {

    private final StarshipService starshipService;
    private final StarshipResponseMapper starshipResponseMapper;

    public StarshipController(StarshipService starshipService, StarshipResponseMapper starshipResponseMapper) {
        this.starshipService = starshipService;
        this.starshipResponseMapper = starshipResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ListResponse>> findStarship(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageResult<Starship> result = starshipService.findStarship(page, size);

        List<ListResponse> starships = result.content().stream().map(starship -> ListResponse.builder().id(starship.getId()).name(starship.getName()).url(starship.getUrl()).build()).toList();

        PageResponse<ListResponse> response = PageResponse.<ListResponse>builder().content(starships).page(result.page()).size(result.size()).totalElements(result.totalElements()).totalPages(result.totalPages()).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipResponse> findPersonById(@PathVariable Long id) {

        //con id = 1 falla -> no hay starship -> hacer excepcion porque hay 30 starships pero no son ids corridos
        return starshipService.findStarshipById(id)
                .map(starshipResponseMapper::toResponse)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public ResponseEntity<List<StarshipResponse>> findPersonByName(@RequestParam String name) {
        List<StarshipResponse> starShips = starshipService.findStarshipByName(name)
                .stream()
                .map(starshipResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(starShips);
    }

}
