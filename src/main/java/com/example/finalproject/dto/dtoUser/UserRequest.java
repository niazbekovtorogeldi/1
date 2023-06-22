package com.example.finalproject.dto.dtoUser;

import com.example.finalproject.enam.Role;
import com.example.finalproject.validation.EmailValid;
import com.example.finalproject.validation.PasswordValid;
import com.example.finalproject.validation.PhoneNumberValid;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record UserRequest(        String firstName,
                                  String lastName,
                                  int age,
                                  @EmailValid
                                          @Column(unique = true)
                                  String email,
                                  @Size(min = 7, max = 15, message = "Password must be exactly 7 characters long")
                                  String password,
                                  @PhoneNumberValid
                                  String phoneNumber,
                                  @Enumerated(EnumType.STRING)
                                  Role role,
                                  int experience


) {
}
