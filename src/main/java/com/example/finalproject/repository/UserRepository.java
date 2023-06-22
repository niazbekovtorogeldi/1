package com.example.finalproject.repository;

import com.example.finalproject.dto.dtoUser.UserResponse;
import com.example.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

    Optional<UserResponse> findUserById(Long id);
    @Query("SELECT COUNT(u) FROM User u WHERE u.restaurant=:restaurantId")
    Optional<User> getUserCountByRestaurantId(@Param("restaurantId") Long restaurantId);



    @Query("select new com.example.finalproject.dto.dtoUser.UserResponse (u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.expirense,u.age)from  User  u")
    List<UserResponse> getAllUser();
    boolean existsByEmail(String email);

}
