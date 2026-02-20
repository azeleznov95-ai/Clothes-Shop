package com.example.clothesshop.dto;

import com.example.clothesshop.enums.OrderStatusEnum;
import com.example.clothesshop.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class OrderResponseDto {
    private Long userId;
    private OrderStatusEnum orderStatus;
    private List<OrderItem> orderItems = new ArrayList<>();

}
