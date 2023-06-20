package com.example.finalproject.dto.dtoMenultem;

import lombok.Builder;

import java.util.List;
@Builder
public record MenuItemResponse(
        Long id,
        String name,
        List<String> image,
        int price,
        String description,
        boolean isVegetarian



) {
    public MenuItemResponse {
    }
}
