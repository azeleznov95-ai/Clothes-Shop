package com.example.clothesshop.security;

import com.example.clothesshop.exeptions.IdIsInvalidException;
import com.example.clothesshop.exeptions.IncorrectLoginOrPasswordException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "app.jwt")

@Component
@Getter
@Setter
public class JWToken {
    private String secret;
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateJWT(String username){
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,username);

    }
    public String createToken(Map<String,Object>claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public Long extractUserId(String token) {
        Claims claims = parseClaims(token);
        Object raw = claims.get("userId");
        System.out.println("userId value = " + raw + ", type = " + (raw == null ? null : raw.getClass()));
        Long id = Long.parseLong( claims.get("userId",String.class));
        if (id!=null){
            return id;
        }
        throw new IdIsInvalidException("Id is invalid");
    }
    public String extractLogin(String token){
        Claims claims = parseClaims(token);
        String login = claims.get("login",String.class);

        if (login!=null && !login.isBlank()){
            return login;
        };
        throw new IncorrectLoginOrPasswordException("Login is invalid");
    }
    public boolean isValid(String token){
        try{
            parseClaims(token);
            return true;
        }
        catch (RuntimeException ex){
            return false;
        }

    }

}

