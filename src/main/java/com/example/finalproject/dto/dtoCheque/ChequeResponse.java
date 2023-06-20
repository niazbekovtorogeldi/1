package com.example.finalproject.dto.dtoCheque;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ChequeResponse(
        Long id,
        int priceAverage,
         ZonedDateTime createAt) {
    public ChequeResponse {
    }
}
