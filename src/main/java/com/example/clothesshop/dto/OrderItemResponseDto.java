package com.example.clothesshop.dto;

import com.example.clothesshop.enums.SizeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
    private Integer priceSnapshot;
    private ClothesResponseDto clothesResponseDto;

    private Integer amount;
    private String imageUrl;
    private OrderResponseDto orderResponse;
    private SizeEnum size;
}
