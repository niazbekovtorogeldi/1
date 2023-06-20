package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoRestaurant.RestaurantRequest;
import com.example.finalproject.dto.dtoRestaurant.RestaurantResponse;
import com.example.finalproject.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantApi {
    private  final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantResponse>restaurantResponses(){
        return restaurantService.getAllRestaurant();
    }
    @PostMapping
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantService.saveRestaurant(restaurantRequest);
    }
    @GetMapping("/{id}")
    public RestaurantResponse getById(@PathVariable Long id){
        return restaurantService.getRestaurantById(id);
    }
    @PutMapping("/{id}")
    public SimpleResponse updateRestaurant(@PathVariable Long id,@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.updateRestaurand(id,restaurantRequest);
    }
    @DeleteMapping("{id}")
    public SimpleResponse deleted(@PathVariable Long id){
        return restaurantService.delete(id);
    }
}
