package com.example.finalproject.repository;

import com.example.finalproject.dto.dtoRestaurant.RestaurantResponse;
import com.example.finalproject.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("select new com.example.finalproject.dto.dtoRestaurant.RestaurantResponse(r.id,r.name,r.location,r.restType,r.numberOfEmployees,r.service)from  Restaurant r")
    List<RestaurantResponse> getAllRestaurant();
    RestaurantResponse getRestaurantById(Long restaurantId);
}
