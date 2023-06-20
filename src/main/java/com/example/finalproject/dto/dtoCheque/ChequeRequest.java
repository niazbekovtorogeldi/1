package com.example.finalproject.dto.dtoCheque;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record ChequeRequest(
        int priceAverage,
        ZonedDateTime createAt) {
}
