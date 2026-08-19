package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.application.service.PeopleService;
import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.PeopleResponse;
import com.bautistagaber.connectiontoswapi.presentation.mapper.PeopleResponseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeopleService peopleService;

    @MockitoBean
    private PeopleResponseMapper peopleResponseMapper;

    @MockitoBean
    private JwtPort jwtPort;

    @MockitoBean
    private UserPersistencePort userPersistencePort;

    @Test
    @WithMockUser
    void findPeople_returnsPaginatedResults() throws Exception {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");
        PageResult<People> pageResult = new PageResult<>(List.of(luke), 0, 10, 82, 9);

        when(peopleService.findPeople(eq(0), eq(10), isNull())).thenReturn(pageResult);

        mockMvc.perform(get("/api/people")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.totalElements").value(82))
                .andExpect(jsonPath("$.totalPages").value(9));
    }

    @Test
    @WithMockUser
    void findPeople_withName_returnsFilteredResults() throws Exception {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");
        PageResult<People> pageResult = new PageResult<>(List.of(luke), 0, 10, 1, 1);

        when(peopleService.findPeople(eq(0), eq(10), eq("Luke"))).thenReturn(pageResult);

        mockMvc.perform(get("/api/people")
                        .param("page", "0")
                        .param("size", "10")
                        .param("name", "Luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"));
    }

    @Test
    @WithMockUser
    void findPersonById_returnsPerson() throws Exception {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");

        when(peopleService.findPersonById(1L)).thenReturn(Optional.of(luke));
        when(peopleResponseMapper.toResponse(luke)).thenReturn(
                PeopleResponse.builder()
                        .id(1L).name("Luke Skywalker").build()
        );

        mockMvc.perform(get("/api/people/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Luke Skywalker"));
    }

    @Test
    @WithMockUser
    void findPersonById_notFound_returns404() throws Exception {
        when(peopleService.findPersonById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/people/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    void findPeople_withoutAuth_returns401() throws Exception {
        mockMvc.perform(get("/api/people"))
                .andExpect(status().isUnauthorized());
    }
}
