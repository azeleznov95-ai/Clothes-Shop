package com.example.personalfinancetracker.controller;

import com.example.personalfinancetracker.dto.AuthorizationRequest;
import com.example.personalfinancetracker.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthorizationService authService;
    AuthController(AuthorizationService authService){
        this.authService=authService;
    }
    @PostMapping("/auth/register")
    public ResponseEntity<String> registration(@RequestBody @Valid AuthorizationRequest authRequest){
        String login = authRequest.getLogin();
        String password=authRequest.getPassword();
        String jwToken =authService.register(login,password);
        return ResponseEntity.status(201).body(jwToken);


    }
    @PostMapping("/auth/login")
    public  ResponseEntity<String> login(@RequestBody @Valid AuthorizationRequest authRequest){
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();
        String jwToken = authService.login(login,password);
        return ResponseEntity.status(200).header("Authorization", jwToken).body(jwToken);
    }
}
