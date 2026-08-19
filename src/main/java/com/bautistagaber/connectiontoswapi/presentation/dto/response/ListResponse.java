package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

/**
 * Lightweight list-item response DTO (id, name, url) for paginated results.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse {
    private Long id;

    private String name;

    private String url;
}
