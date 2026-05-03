package com.example.clothesshop.controller;



import com.example.clothesshop.dto.OrderFromOneClothDto;
import com.example.clothesshop.dto.OrderResponseDto;
import com.example.clothesshop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    OrderService orderService;
    OrderController(OrderService orderService){
        this.orderService=  orderService;

    }
    @PostMapping("/cart")
    public ResponseEntity<OrderResponseDto> setOrderFromCart(@CookieValue(value = "access_token", required = false) String token){
        var response = orderService.setOrderFromCart(token);
        return ResponseEntity.ok(response);

    }

}
