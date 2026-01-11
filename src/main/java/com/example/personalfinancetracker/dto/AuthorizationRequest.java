package com.example.personalfinancetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class AuthorizationRequest {
    @NonNull
    @NotBlank
    String login;
    @NonNull
    @NotBlank
    String password;
}
