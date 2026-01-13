package com.example.personalfinancetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
@EnableWebSecurity

public class SpringSecurity {
    @Bean
    @Order(1)
    public SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception{
        http.securityMatcher("/auth/**")
                .authorizeHttpRequests(authz ->authz.anyRequest().permitAll())
                .csrf(csrf->csrf.disable());
        return http.build();
    }
}
