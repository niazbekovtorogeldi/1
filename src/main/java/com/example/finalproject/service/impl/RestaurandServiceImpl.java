package com.example.finalproject.service.impl;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoRestaurant.RestaurantRequest;
import com.example.finalproject.dto.dtoRestaurant.RestaurantResponse;
import com.example.finalproject.entity.Restaurant;
import com.example.finalproject.repository.RestaurantRepository;
import com.example.finalproject.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RestaurandServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    @Override
    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantRepository.getAllRestaurant();
    }


    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant= new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setService(restaurantRequest.service());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees());
        restaurant.setRestType(restaurantRequest.restType());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format(""))
                .build();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        return restaurantRepository.getRestaurantById(restaurantId);
    }

    @Override
    public SimpleResponse updateRestaurand(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new NullPointerException(""));
        restaurant.setName(restaurantRequest.name());
        restaurant.setService(restaurantRequest.service());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setNumberOfEmployees(restaurant.getNumberOfEmployees());
        restaurant.setRestType(restaurantRequest.restType());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("updated restoran"+restaurantId))
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new NullPointerException());
        restaurantRepository.delete(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("deleted restoran"+id))
                .build();
    }
}
