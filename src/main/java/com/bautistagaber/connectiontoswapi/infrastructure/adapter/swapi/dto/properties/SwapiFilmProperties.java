package com.bautistagaber.connectiontoswapi.infrastructure.adapter.swapi.dto.properties;

import java.util.List;

/**
 * DTO mapping SWAPI film JSON properties from the external API (snake_case).
 */
public record SwapiFilmProperties(String created,
                                  String edited,
                                  List<String> starships,
                                  List<String> vehicles,
                                  List<String> planets,
                                  String producer,
                                  String title,
                                  Integer episode_id,
                                  String director,
                                  String release_date,
                                  String opening_crawl,
                                  List<String> characters,
                                  List<String> species,
                                  String url) {

}
