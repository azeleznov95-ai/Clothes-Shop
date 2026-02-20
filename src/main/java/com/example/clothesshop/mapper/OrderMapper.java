package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.OrderResponseDto;
import com.example.clothesshop.model.Orders;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toResponse(Orders entity,Long userId){
        var response = new OrderResponseDto();
        response.setOrderStatus(entity.getOrderStatus());
        response.setUserId(userId);
        response.setOrderItems(entity.getOrderItems());
        return response;
    }
}
