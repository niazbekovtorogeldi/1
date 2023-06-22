package com.example.finalproject.service.impl;


import com.example.finalproject.dto.SimpleResponse.SimpleResponse;
import com.example.finalproject.dto.dtoUser.UserRequest;
import com.example.finalproject.dto.dtoUser.UserResponse;


import com.example.finalproject.enam.Role;
import com.example.finalproject.entity.Restaurant;
import com.example.finalproject.entity.User;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.repository.RestaurantRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUser();
    }



    @Override
    public SimpleResponse saveUser(UserRequest userRequest) {
        int age = userRequest.age();
        int experience = userRequest.experience();
        Role role = userRequest.role();
        if (role == Role.CHEF && age > 25 && age <= 45 && experience >= 2) {
            User chef = createUser(userRequest);
            return saveUser(chef, "CHEF");
        } else if (role == Role.WAITER && age > 18 && age <= 30 && experience >= 1) {
            User waiter = createUser(userRequest);
            return saveUser(waiter, "WAITER");
        } else {
            log.error("Invalid age or experience for the given role!");
            throw new NullPointerException("Invalid age or experience for the given role!");
        }
    }

    private User createUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setAge(userRequest.age());
        user.setPhoneNumber(userRequest.phoneNumber());
        user.setExpirense(userRequest.experience());
        user.setDateOfBirth(ZonedDateTime.now());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(userRequest.role());
        return user;

    }

    private SimpleResponse saveUser(User user, String roleName) {
        userRepository.save(user);
        log.info(String.format("%s with name %s successfully saved !", roleName, user.getFirstName()));
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("%s with name %s successfully saved !", roleName, user.getFirstName()))
                .build();

    }
    @Override
    public SimpleResponse assineUserToRestoran(Long userId, Long restoranId) {
        Restaurant restaurant = restaurantRepository.findById(restoranId)
                .orElseThrow(() -> {
                    log.error("Restaurant with id  " + restoranId + " not found !");
                    return new NotFoundException("Restaurant with id  " + restoranId + " not found !");
                });
        User user = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User with id  " + userId + " not  found !");
            return new NotFoundException("User with id  " + userId + " not  found !");
        });
        if (restaurant.getUsers().size() <= 15) {
            userRepository.save(user);

            List<User> users = new ArrayList<>();
            users.add(user);
            restaurant.setUsers(users);
            user.setRestaurant(restaurant);
            restaurantRepository.save(restaurant);

            log.info(String.format("User with id %s successfully added restaurant with id %s !", userId, restoranId));
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(String.format("User with id %s successfully added restaurant with id %s !", userId, restoranId))
                    .build();

        } else {
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.CONFLICT)
                    .message("Restaurant user count didn't more 15 !")
                    .build();
        }
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
        user.setExpirense(userRequest.experience());
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
    @Override
    public boolean existByEmail(String email) {
        String queryString = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        Long count = entityManager.createQuery(queryString, Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

}
