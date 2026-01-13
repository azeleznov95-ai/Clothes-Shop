package com.example.personalfinancetracker.dto;

import com.example.personalfinancetracker.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
  private int amount;
  private String description;
  private Long categoryId;
}
