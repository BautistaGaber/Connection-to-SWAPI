package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.StarshipService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.Starship;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.ListResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.StarshipResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.StarshipResponseMapper;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/starships")
@Validated
@SecurityRequirement(name = "bearerAuth")
public class StarshipController {

    private final StarshipService starshipService;
    private final StarshipResponseMapper starshipResponseMapper;

    public StarshipController(StarshipService starshipService, StarshipResponseMapper starshipResponseMapper) {
        this.starshipService = starshipService;
        this.starshipResponseMapper = starshipResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ListResponse>> findStarship(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        PageResult<Starship> result = starshipService.findStarship(page, size);

        List<ListResponse> starships = result.content().stream().map(starship -> ListResponse.builder().id(starship.getId()).name(starship.getName()).url(starship.getUrl()).build()).toList();

        PageResponse<ListResponse> response = PageResponse.<ListResponse>builder().content(starships).page(result.page()).size(result.size()).totalElements(result.totalElements()).totalPages(result.totalPages()).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StarshipResponse> findPersonById(@PathVariable @Positive Long id) {

        return starshipService.findStarshipById(id)
                .map(starshipResponseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Starship", id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<StarshipResponse>> findPersonByName(@RequestParam @NotBlank String name) {
        List<StarshipResponse> starShips = starshipService.findStarshipByName(name)
                .stream()
                .map(starshipResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(starShips);
    }

}
