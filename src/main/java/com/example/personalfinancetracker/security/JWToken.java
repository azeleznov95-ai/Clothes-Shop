package com.example.personalfinancetracker.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
@ConfigurationProperties(prefix = "app.jwt")
@Component
@Getter
@Setter
public class JWToken {
    private String secret;
    public String generateJWT(String username){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,username);

    }
    public String createToken(Map<String,Object>claims,String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60* 10))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

}

