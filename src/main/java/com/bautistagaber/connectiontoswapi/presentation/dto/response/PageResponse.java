package com.bautistagaber.connectiontoswapi.presentation.dto.response;

import lombok.*;

import java.util.List;

/**
 * Generic paginated response DTO with content, page, size, totalElements, and totalPages.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;

    private int page;

    private int size;

    private int totalElements;

    private int totalPages;
}
