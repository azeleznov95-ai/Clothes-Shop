package com.example.personalfinancetracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    int status;
    String message;
    LocalDateTime timestamp;
}
