package com.example.clothesshop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SpringSecurity {
    @Bean
    @Order(1)
    public SecurityFilterChain Endpoints(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) // Для примера, с JWT обычно disable
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .requestMatchers("/api/cart/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll()
                );
                return http.build();
    }

}
