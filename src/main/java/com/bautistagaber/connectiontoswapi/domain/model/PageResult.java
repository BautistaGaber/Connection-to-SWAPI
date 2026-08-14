package com.bautistagaber.connectiontoswapi.domain.model;

import java.util.List;

public record PageResult<T>(List<T> content, int page, int size, int totalElements, int totalPages) {
}
