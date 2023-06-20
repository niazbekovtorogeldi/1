package com.example.finalproject.dto.dtoCategory;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

@Builder
public record CategoryResponse(
        Long id,
        String name


) {
    public CategoryResponse {
    }
}
