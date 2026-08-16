package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleListResponse {
    private Long id;

    private String name;

    private String url;
}
