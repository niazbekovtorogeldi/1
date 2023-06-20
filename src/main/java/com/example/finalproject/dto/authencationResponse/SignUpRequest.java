package com.example.finalproject.dto.authencationResponse;

import com.example.finalproject.enam.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(String firstName,
                            String lastName,
                            int age,
                            String email,
                            String password,
                            @Pattern(regexp = "\\+996\\d{9}", message = "Phone number is not valid")
                            String phoneNumber,
                            @Enumerated(EnumType.STRING)
                            Role role,
                            int experience) {
    public SignUpRequest {
    }
}
