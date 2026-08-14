package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PageResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.PeopleResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleResponseMapper peopleResponseMapper;

    public PeopleController(PeopleService peopleService, PeopleResponseMapper peopleResponseMapper) {
        this.peopleService = peopleService;
        this.peopleResponseMapper = peopleResponseMapper;
    }

    public ResponseEntity<PageResponse<PeopleResponse>> findPeople(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String name) {
        PageResult<People> result = peopleService.findPeople(page, size, name);

        List<PeopleResponse> people = result.content().stream().map(peopleResponseMapper::toResponse).toList();

        PageResponse<PeopleResponse> response = PageResponse.<PeopleResponse>builder().content(people).page(result.page()).size(result.size()).totalElements(result.totalElements()).totalPages(result.totalPages()).build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponse> findPersonById(@PathVariable Long id) {

        return peopleService.findPersonById(id)
                .map(peopleResponseMapper::toResponse)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
