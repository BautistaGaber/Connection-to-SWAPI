package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.list;


//se necesita para listar todos los "people, starships y vehicles"
public record SwapiListItem(String uid, String name, String url) { }
