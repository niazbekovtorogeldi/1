package com.example.finalproject.dto.authencationResponse;

import lombok.Builder;

import java.awt.desktop.ScreenSleepEvent;

@Builder
public record AdminTokenRequest(String email, String password) {
    public AdminTokenRequest {
    }
}
