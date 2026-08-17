package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.ListResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.PeopleResponseMapper;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@Validated
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleResponseMapper peopleResponseMapper;

    public PeopleController(PeopleService peopleService, PeopleResponseMapper peopleResponseMapper) {
        this.peopleService = peopleService;
        this.peopleResponseMapper = peopleResponseMapper;
    }

    @GetMapping()
    public ResponseEntity<PageResponse<ListResponse>> findPeople(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        PageResult<People> result = peopleService.findPeople(page, size);

        List<ListResponse> people = result.content().stream().map(person -> ListResponse.builder().id(person.getId()).name(person.getName()).url(person.getUrl()).build()).toList();

        PageResponse<ListResponse> response = PageResponse.<ListResponse>builder().content(people).page(result.page()).size(result.size()).totalElements(result.totalElements()).totalPages(result.totalPages()).build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponse> findPersonById(@PathVariable @Positive Long id) {

        return peopleService.findPersonById(id)
                .map(peopleResponseMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Person", id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<PeopleResponse>> findPersonByName(@RequestParam @NotBlank String name) {
        List<PeopleResponse> people = peopleService.findPersonByName(name)
                .stream()
                .map(peopleResponseMapper::toResponse)
                .toList();

        return ResponseEntity.ok(people);
    }

}
