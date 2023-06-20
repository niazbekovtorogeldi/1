package com.example.finalproject.service;

import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoUser.UserRequest;
import com.example.finalproject.dto.dtoUser.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse>getAllUsers();
    SimpleResponse saveUser(UserRequest userRequest);

  SimpleResponse assineUserToRestoran(Long userId,Long restoranId);

    UserResponse getUserById(Long userId);
    SimpleResponse updateUser(Long userId,UserRequest userRequest);
    SimpleResponse deletedUser(Long userId);


}
