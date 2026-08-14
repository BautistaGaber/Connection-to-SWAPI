package com.bautistagaber.connectiontoswapi.domain.model;


import lombok.*;

import java.time.Instant;
import java.util.List;

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
}
