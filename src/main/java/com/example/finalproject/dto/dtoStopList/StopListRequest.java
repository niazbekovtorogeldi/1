package com.example.finalproject.dto.dtoStopList;

import lombok.Builder;

import java.time.ZonedDateTime;
@Builder
public record StopListRequest(
         String reason,
         ZonedDateTime date
) {
}
