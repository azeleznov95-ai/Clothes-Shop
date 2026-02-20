package com.example.clothesshop.dto;

import com.example.clothesshop.enums.CartStatusEnum;
import com.example.clothesshop.model.CartItem;


import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponseDto {
        private Long userId;

        private List<CartItem> cartItems = new ArrayList<>();
        private CartStatusEnum cartStatus = CartStatusEnum.EMPTY;
        private Integer totalPrice;





}
