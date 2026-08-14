package com.bautistagaber.connectiontoswapi.domain.port.out;

import java.util.List;

public record SwapiPage<T> (List<T> content,
                         int page,
                         int size,
                         int totalElements,
                         int totalPages){
}
