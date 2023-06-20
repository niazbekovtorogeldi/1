package com.example.finalproject.dto.dtoUser;

import com.example.finalproject.enam.Role;
import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record UserRequest(
        String firstName,
        String lastName,
        ZonedDateTime dateOfBirth,
        String email,
        String password,
        String phoneNumber,
        Role role,
        int  expirense,
        int age
) {
}
