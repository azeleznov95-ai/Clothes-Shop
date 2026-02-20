package com.example.clothesshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class AuthorizationRequestDto {
    @NonNull
    @NotBlank
    String login;
    @NonNull
    @NotBlank
    String password;
    @NonNull
    @NotBlank
    String telegramUserName;
}
