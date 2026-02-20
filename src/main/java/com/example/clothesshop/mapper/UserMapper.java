package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.UserShortResponseDto;
import com.example.clothesshop.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
public UserShortResponseDto toResponse(Users entity){
    UserShortResponseDto response = new UserShortResponseDto();
    response.setId(entity.getId());
    response.setLogin(entity.getLogin());
    response.setTelegramUsername(entity.getTelegramUsername());
    return response;
}
}
