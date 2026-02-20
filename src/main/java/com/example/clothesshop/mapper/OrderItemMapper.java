package com.example.clothesshop.mapper;


import com.example.clothesshop.model.CartItem;
import com.example.clothesshop.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem cartItemToOrderItem(CartItem cartItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(cartItem.getAmount());
        orderItem.setPriceSnapshot(cartItem.getPriceSnapshot());
        orderItem.setCloth(cartItem.getCloth());
        orderItem.setImageUrl(cartItem.getImageUrl());
        orderItem.setSize(cartItem.getSize());
        return orderItem;

    }
}
