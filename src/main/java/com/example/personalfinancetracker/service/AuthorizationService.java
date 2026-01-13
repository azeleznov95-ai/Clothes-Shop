package com.example.personalfinancetracker.service;

import com.example.personalfinancetracker.exeptions.ConflictException;
import com.example.personalfinancetracker.exeptions.IncorrectLoginOrPasswordException;
import com.example.personalfinancetracker.model.User;
import com.example.personalfinancetracker.repository.UserRepository;
import com.example.personalfinancetracker.security.JWToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String register(String login, String password) throws ConflictException, IncorrectLoginOrPasswordException {
        Optional<User> userRepositoryEntity =userRepository.findUserByLogin(login);
        if (userRepositoryEntity.isPresent()){
            throw new ConflictException("Login already exists");
        }
        String passwordHash = encoder.encode(password);
        User userEntity = new User();
        userEntity.setPasswordHash(passwordHash);
        userEntity.setLogin(login);
        userRepository.save(userEntity);
        return login(login,password);
    }
    public String login(String login,String passwordHash) throws IncorrectLoginOrPasswordException {
        Optional<User> userEntity = userRepository.findUserByLogin(login);
        if (userEntity.isEmpty()){
            throw new IncorrectLoginOrPasswordException("No user with this login");
        }
        if (encoder.matches(passwordHash,userEntity.get().getPasswordHash())){
            Long userId = userEntity.get().getId();
            HashMap<String,Object> claims=new HashMap<>(){};
            claims.put("login",login);
            claims.put("userId",String.valueOf(userId));
        return jwToken.createToken(claims,login);
        }
        throw new IncorrectLoginOrPasswordException("Login or password is incorrect");
    }

}
