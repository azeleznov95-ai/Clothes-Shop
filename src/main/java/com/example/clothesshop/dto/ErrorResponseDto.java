package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDto {
    int status;
    String message;
    LocalDateTime timestamp;
}
