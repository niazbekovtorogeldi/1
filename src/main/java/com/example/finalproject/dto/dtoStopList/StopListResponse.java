package com.example.finalproject.dto.dtoStopList;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record StopListResponse(
        Long id,
        String reason,
        ZonedDateTime date



) {
    public StopListResponse {
    }
}
