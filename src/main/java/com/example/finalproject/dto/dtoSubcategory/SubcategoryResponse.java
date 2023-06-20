package com.example.finalproject.dto.dtoSubcategory;

import lombok.Builder;

@Builder
public record SubcategoryResponse(
        Long id,
        String name


) {
    public SubcategoryResponse {
    }
}
