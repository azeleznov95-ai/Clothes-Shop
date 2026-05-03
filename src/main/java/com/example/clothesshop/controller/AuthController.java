package com.example.clothesshop.controller;

import com.example.clothesshop.dto.AuthorizationRequestDto;
import com.example.clothesshop.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthorizationService authService;

    AuthController(AuthorizationService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registration(@RequestBody @Valid AuthorizationRequestDto authRequest){
        ResponseCookie accessTokenCookie = authService.register(authRequest);
        return buildAuthResponse(HttpStatus.CREATED, accessTokenCookie);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid AuthorizationRequestDto authRequest){
        ResponseCookie accessTokenCookie = authService.login(authRequest);
        return buildAuthResponse(HttpStatus.OK, accessTokenCookie);
    }

    private ResponseEntity<Void> buildAuthResponse(HttpStatus status, ResponseCookie accessTokenCookie) {
        return ResponseEntity.status(status)
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .build();
    }
}
