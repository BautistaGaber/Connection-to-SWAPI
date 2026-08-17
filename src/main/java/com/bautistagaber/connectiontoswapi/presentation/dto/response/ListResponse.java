package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse {
    private Long id;

    private String name;

    private String url;
}
