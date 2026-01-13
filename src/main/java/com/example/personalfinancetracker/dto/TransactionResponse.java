package com.example.personalfinancetracker.dto;

import com.example.personalfinancetracker.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
    private int amount;
    private String description;
    private Category categoryName;
    private LocalDateTime createdAt;
}

