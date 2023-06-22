package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoRestaurant.RestaurantRequest;
import com.example.finalproject.dto.dtoRestaurant.RestaurantResponse;
import com.example.finalproject.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponse> getAllRestaurant();

    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurantById(Long restaurantId);
    SimpleResponse updateRestaurand(Long restaurantId,RestaurantRequest restaurantRequest);
    SimpleResponse delete(Long id);



}
