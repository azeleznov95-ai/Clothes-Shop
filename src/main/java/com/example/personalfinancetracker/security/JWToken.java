package com.example.personalfinancetracker.security;

import com.example.personalfinancetracker.exeptions.IdIsInvalid;
import com.example.personalfinancetracker.exeptions.LoginIsInvalid;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public Long extractUserId(String token) {
        Claims claims=  Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        Long id=  claims.get("userId",Long.class);
        if (id!=null){
            return id;
        };
        throw new IdIsInvalid("Id is invalid");
    }
    public String extractLogin(String token){
        Claims claims=  Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String login=claims.get("login",String.class);
        if (login!=null && !login.isBlank()){
            return login;
        };
        throw new LoginIsInvalid("Login is invalid");
    }
    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isValid(String token){
        try{
            extractLogin(token);
            extractUserId(token);
        }
        catch (RuntimeException ex){
            return false;
        }
        return true;
    }

}

