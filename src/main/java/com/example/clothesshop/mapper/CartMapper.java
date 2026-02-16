package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.model.Cart;

import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartResponseDto toResponse(Cart entity,Integer totalPrice){
        CartResponseDto response =new CartResponseDto();
        var items = entity.getCartItems();
        response.setCartItems(items);
        response.setCartStatus(entity.getCartStatus());
        response.setTotalPrice(totalPrice);
        response.setUserId(entity.getId());
        return response;
    }
    public Cart toEntity(CartResponseDto response){
        var entity = new Cart();
        entity.setUserId(response.getUserId());
        entity.setCartStatus(response.getCartStatus());
        entity.setCartItems(response.getCartItems());
        return entity;
    }

}
