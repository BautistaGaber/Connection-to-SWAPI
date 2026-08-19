package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.domain.model.PageResult;
import com.bautistagaber.connectiontoswapi.domain.model.People;
import com.bautistagaber.connectiontoswapi.domain.port.out.SwapiPort;
import com.bautistagaber.connectiontoswapi.presentation.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceImplTest {

    @Mock
    private SwapiPort swapiPort;

    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Test
    void findPeople_withName_returnsResults() {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");
        PageResult<People> pageResult = new PageResult<>(List.of(luke), 0, 10, 1, 1);

        when(swapiPort.findPersonByName("Luke", 0, 10)).thenReturn(pageResult);

        PageResult<People> result = peopleService.findPeople(0, 10, "Luke");

        assertEquals(1, result.content().size());
        assertEquals("Luke Skywalker", result.content().getFirst().getName());
    }

    @Test
    void findPeople_withNameEmpty_throwsResourceNotFoundException() {
        PageResult<People> emptyResult = new PageResult<>(List.of(), 0, 10, 0, 0);

        when(swapiPort.findPersonByName("Invento", 0, 10)).thenReturn(emptyResult);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> peopleService.findPeople(0, 10, "Invento")
        );
        assertTrue(exception.getMessage().contains("Invento"));
    }

    @Test
    void findPeople_withoutName_returnsPaginatedResults() {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");
        PageResult<People> pageResult = new PageResult<>(List.of(luke), 0, 10, 82, 9);

        when(swapiPort.findPeople(0, 10)).thenReturn(pageResult);

        PageResult<People> result = peopleService.findPeople(0, 10, null);

        assertEquals(1, result.content().size());
        assertEquals(82, result.totalElements());
    }

    @Test
    void findPeople_withBlankName_returnsPaginatedResults() {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");
        PageResult<People> pageResult = new PageResult<>(List.of(luke), 0, 10, 82, 9);

        when(swapiPort.findPeople(0, 10)).thenReturn(pageResult);

        PageResult<People> result = peopleService.findPeople(0, 10, "   ");

        assertEquals(1, result.content().size());
    }

    @Test
    void findPersonById_returnsPerson() {
        People luke = new People(1L, "Luke Skywalker", "https://swapi.tech/api/people/1");

        when(swapiPort.findPersonById(1L)).thenReturn(Optional.of(luke));

        Optional<People> result = peopleService.findPersonById(1L);

        assertTrue(result.isPresent());
        assertEquals("Luke Skywalker", result.get().getName());
    }

    @Test
    void findPersonById_returnsEmpty_whenNotFound() {
        when(swapiPort.findPersonById(999L)).thenReturn(Optional.empty());

        Optional<People> result = peopleService.findPersonById(999L);

        assertTrue(result.isEmpty());
    }
}
