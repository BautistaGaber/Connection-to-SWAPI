package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleResponse {
    private Long id;

    private String name;

    private String birthYear;

    private String eyeColor;

    private List<String> films;

    private String gender;

    private String hairColor;

    private String height;

    private String homeworld;

    private String mass;

    private String skinColor;

    private Instant created;

    private Instant edited;

    private List<String> species;

    private List<String> starships;

    private String url;

    private List<String> vehicles;
}
