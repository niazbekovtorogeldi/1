package com.example.finalproject.service.impl;

import com.example.finalproject.confic.JwtService;
import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoUser.UserRequest;
import com.example.finalproject.dto.dtoUser.UserResponse;
import com.example.finalproject.enam.Role;
import com.example.finalproject.entity.Restaurant;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.RestaurantRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
private final JwtService jwtService;



//    private User getAuthentication() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User with email:" + email + " is not found!"));
//        return user;
//    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUser();
    }

    @Override
    public SimpleResponse saveUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        if (userRequest.role().equals(Role.CHEF)) {
            if (userRequest.age() > 25 && userRequest.age() <= 45) {
                if (userRequest.expirense() <= 2) {
                    user.setRole(userRequest.role());
                    user.setAge(userRequest.age());
                    user.setExpirense(userRequest.expirense());
                    user.setDateOfBirth(ZonedDateTime.now());
                    List<User> users = new ArrayList<>();
                    users.add(user);
                    userRepository.save(user);
                    if (userRequest.role().equals(Role.WAITER)) {
                        if (userRequest.age() > 18 && userRequest.age() <= 30) {
                            user.setRole(userRequest.role());
                            user.setAge(userRequest.age());
                            user.setExpirense(userRequest.expirense());
                            user.setDateOfBirth(ZonedDateTime.now());
                            userRepository.save(user);
                            return SimpleResponse.builder()
                                    .httpStatus(HttpStatus.OK)
                                    .message(String.format("Waiter with name %s saved successfully " + user.getFirstName()))
                                    .build();
                        }
                    }
                }
            }
        } else {
            throw new UsernameNotFoundException("Waiter age must between 18 and 30 experience must 2 years!");
        }
    return SimpleResponse.builder()
            .httpStatus(HttpStatus.BAD_REQUEST)
            .message(String.format("Uncorrected age or experience!"))
            .build();
    }

    @Override
    public SimpleResponse assineUserToRestoran(Long userId, Long restoranId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
        Restaurant restaurant = restaurantRepository.findById(restoranId).orElseThrow(() -> new NoSuchElementException("Ресторан не найден"));
        user.setRestaurant(restaurant);
        List<User>users=new ArrayList<>();
        users.add(user);
        restaurant.setUsers(users);
        userRepository.save(user);
        restaurantRepository.save(restaurant);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Пользователь успешно привязан к ресторану"+userId))
                .build();


    }


    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(()->new NullPointerException(""));
    }

    @Override
    public SimpleResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("not user "+userId));
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setAge(userRequest.age());
        user.setExpirense(userRequest.expirense());
        userRepository.save(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("update user "+userId))
                .build();
    }

    @Override
    public SimpleResponse deletedUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException());
        userRepository.delete(user);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("deleted user "+userId))
                .build();
    }

}
