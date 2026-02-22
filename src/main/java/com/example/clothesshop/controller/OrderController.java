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
    @PostMapping("/cart/{token}")
    public ResponseEntity<OrderResponseDto> setOrderFromCart(@PathVariable String token){
        var response = orderService.setOrderFromCart(token);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/{token}")
    public ResponseEntity<OrderResponseDto> setOrderFromOneCloth(@PathVariable String token, @RequestBody OrderFromOneClothDto request){
        var response = orderService.setOrderFromOneCloth(token,request);
        return ResponseEntity.ok(response);
    }

}
