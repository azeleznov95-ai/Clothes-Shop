package com.example.clothesshop.service;

import com.example.clothesshop.enums.RolesEnum;
import com.example.clothesshop.exeptions.ConflictException;
import com.example.clothesshop.exeptions.IncorrectLoginOrPasswordException;
import com.example.clothesshop.model.Users;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.security.JWToken;
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
        Optional<Users> userRepositoryEntity =userRepository.findUserByLogin(login);
        if (userRepositoryEntity.isPresent()){
            throw new ConflictException("Login already exists");
        }
        //todo: exception when login is not unique
        String passwordHash = encoder.encode(password);
        Users userEntity = new Users();
        userEntity.setPasswordHash(passwordHash);
        userEntity.setLogin(login);
        userRepository.save(userEntity);
        return login(login,password);
    }
    public String login(String login,String rawPassword) {
        Optional<Users> userEntity = userRepository.findUserByLogin(login);
        if (userEntity.isEmpty()){
            throw new IncorrectLoginOrPasswordException("No user with this login");
        }

        if (encoder.matches(rawPassword,userEntity.get().getPasswordHash())){
            Long userId = userEntity.get().getId();
            HashMap<String,Object> claims=new HashMap<>(){};
            claims.put("login",login);
            claims.put("userId",String.valueOf(userId));
            claims.put("role", RolesEnum.User);
        return jwToken.createToken(claims,login);
        }
        throw new IncorrectLoginOrPasswordException("Login or password is incorrect");
    }

}
