package com.bautistagaber.connectiontoswapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {
    private static final String SWAPI_BASE_URL = "https://www.swapi.tech/api";

    @Bean
    public WebClient swapiWebClient(WebClient.Builder builder){
        return builder.baseUrl(SWAPI_BASE_URL).build();
    }
}
