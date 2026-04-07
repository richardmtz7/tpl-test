package com.terpel.test.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record FilterResponse<T>(
        List<T> content,
        int     page,
        int     size,
        long    totalElements,
        int     totalPages,
        boolean last
) {
    public static <T> FilterResponse<T> from(Page<T> page) {
        return new FilterResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
