package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.OrderItemResponseDto;
import com.example.clothesshop.dto.OrderResponseDto;
import com.example.clothesshop.model.OrderItem;
import com.example.clothesshop.model.Orders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDto toResponse(Orders entity, Long userId) {
        OrderResponseDto resp = new OrderResponseDto();
        resp.setOrderStatus(entity.getOrderStatus());
        List<OrderItemResponseDto> orderItemsResp= new ArrayList<>();
        var orderItems = entity.getOrderItems();
        for (OrderItem item:orderItems ){
            OrderItemResponseDto dto = new OrderItemResponseDto();
            dto.setAmount(item.getAmount());
            dto.setImageUrl(item.getImageUrl());
            dto.setPriceSnapshot(item.getPriceSnapshot());
            dto.setSize(item.getSize());
            orderItemsResp.add(dto);
        }
        resp.setOrderItems(orderItemsResp);
        resp.setUserId(userId);
        return resp;

    }
}
