package com.bautistagaber.connectiontoswapi.domain.model;

import java.time.Instant;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class People {
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

    public People(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
