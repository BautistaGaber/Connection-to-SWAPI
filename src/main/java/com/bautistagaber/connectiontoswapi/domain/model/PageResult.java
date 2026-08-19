package com.bautistagaber.connectiontoswapi.domain.model;

import java.util.List;

/**
 * Generic record that encapsulates paginated results returned by SWAPI.
 */
public record PageResult<T>(List<T> content, int page, int size, int totalElements, int totalPages) {
}
