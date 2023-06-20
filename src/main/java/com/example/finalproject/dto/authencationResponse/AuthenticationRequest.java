package com.example.finalproject.dto.authencationResponse;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String firstName, String lastName, String email, String password) {
    public AuthenticationRequest {
    }
}
