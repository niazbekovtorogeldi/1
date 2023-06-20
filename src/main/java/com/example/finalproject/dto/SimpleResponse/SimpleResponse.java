package com.example.finalproject.dto.SimpleResponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SimpleResponse {

    private HttpStatus httpStatus;
    private String message;
}