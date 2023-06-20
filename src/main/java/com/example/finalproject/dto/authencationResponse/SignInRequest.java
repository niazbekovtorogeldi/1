package com.example.finalproject.dto.authencationResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class
SignInRequest {
    private String email;
    private String password;

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
