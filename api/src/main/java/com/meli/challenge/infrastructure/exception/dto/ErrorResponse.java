package com.meli.challenge.infrastructure.exception.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Instant timestamp;
    private int status;
}
