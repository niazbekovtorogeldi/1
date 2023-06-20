package com.example.finalproject.dto.dtoRestaurant;

import com.example.finalproject.enam.RestType;
import lombok.Builder;

@Builder
public record RestaurantRequest(
         String name,
         String location,
         RestType restType,
         int numberOfEmployees,
         int service
) {
}
