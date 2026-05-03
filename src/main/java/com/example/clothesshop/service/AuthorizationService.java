package com.example.clothesshop.service;

import com.example.clothesshop.dto.AuthorizationRequestDto;
import com.example.clothesshop.enums.RolesEnum;
import com.example.clothesshop.exeptions.ConflictException;
import com.example.clothesshop.exeptions.IncorrectLoginOrPasswordException;
import com.example.clothesshop.model.Users;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.security.JWToken;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthorizationService {
    private final JWToken jwToken;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    AuthorizationService(UserRepository userRepository,PasswordEncoder encoder,JWToken jwToken){
        this.userRepository=userRepository;
        this.encoder=encoder;
        this.jwToken = jwToken;

    }
    public ResponseCookie register(AuthorizationRequestDto authRequest)  {
        String login = authRequest.getLogin();
        Optional<Users> userRepositoryEntity =userRepository.findUserByLogin(login);
        if (userRepositoryEntity.isPresent()){
            throw new ConflictException("Login already exists");
        }

        String passwordHash = encoder.encode(authRequest.getPassword());
        Users userEntity = new Users();
        userEntity.setPasswordHash(passwordHash);
        userEntity.setLogin(login);
        userRepository.save(userEntity);
        return login(authRequest);
    }

    public ResponseCookie login(AuthorizationRequestDto authRequest) {
        String login = authRequest.getLogin();
        Optional<Users> userEntity = userRepository.findUserByLogin(login);
        if (userEntity.isEmpty()){
            throw new IncorrectLoginOrPasswordException("No user with this login");
        }

        if (encoder.matches(authRequest.getPassword(), userEntity.get().getPasswordHash())){
            Long userId = userEntity.get().getId();
            HashMap<String,Object> claims=new HashMap<>(){};
            claims.put("login",login);
            claims.put("userId",String.valueOf(userId));
            claims.put("role", RolesEnum.User);
            var jwt = jwToken.createToken(claims, login);
            return buildAccessTokenCookie(jwt);
        }
        throw new IncorrectLoginOrPasswordException("Login or password is incorrect");
    }

    private ResponseCookie buildAccessTokenCookie(String jwt) {
        return ResponseCookie.from("access_token", jwt)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofHours(10))
                .build();
    }
}
