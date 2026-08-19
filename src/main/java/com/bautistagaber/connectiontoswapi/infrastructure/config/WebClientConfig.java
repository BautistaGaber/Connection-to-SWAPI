package com.bautistagaber.connectiontoswapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for Spring WebClient pointing to the SWAPI base URL (https://www.swapi.tech/api).
 */
@Configuration
public class WebClientConfig {
    private static final String SWAPI_BASE_URL = "https://www.swapi.tech/api";

    @Bean
    public WebClient swapiWebClient() {
        return WebClient.builder().baseUrl(SWAPI_BASE_URL).build();
    }
}
