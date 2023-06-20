package com.example.finalproject.api;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoUser.UserRequest;
import com.example.finalproject.dto.dtoUser.UserResponse;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserApi {
    private final UserService userService;

    @GetMapping
    public List<UserResponse>userResponses(){
        return userService.getAllUsers();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{restaurantId}")
    public SimpleResponse saveUser(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);

    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{userId}")
    public SimpleResponse updateUser(@PathVariable  Long userId, @RequestBody UserRequest userRequest){
        return userService.updateUser(userId,userRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{userId}/{restoranId}")
    public SimpleResponse assin(@PathVariable Long userId,@PathVariable Long restoranId){
        return userService.assineUserToRestoran(userId,restoranId);
    }
    @DeleteMapping("{id}")
    public SimpleResponse deleted(@PathVariable Long id){
        return userService.deletedUser(id);
    }
}
