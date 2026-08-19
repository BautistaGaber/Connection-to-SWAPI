package com.bautistagaber.connectiontoswapi.domain.model;


import lombok.*;

import java.time.Instant;
import java.util.List;

/**
 * Domain model representing a Star Wars vehicle.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String name;
    private String model;
    private String vehicleClass;
    private String manufacturer;
    private String length;
    private String costInCredits;
    private String crew;
    private String passengers;
    private String maxAtmospheringSpeed;
    private String cargoCapacity;
    private String consumables;
    private List<String> films;
    private List<String> pilots;
    private String url;
    private Instant created;
    private Instant edited;


    public Vehicle(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
