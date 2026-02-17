package com.example.clothesshop.controller;



import com.example.clothesshop.dto.CartItemToChangeAmountRequestDto;
import com.example.clothesshop.dto.CartResponseDto;

import com.example.clothesshop.enums.SizeEnum;
import com.example.clothesshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    CartController(CartService cartService){
        this.cartService = cartService;
    }
    @GetMapping("/cart")
    public ResponseEntity<CartResponseDto> getCart(@RequestParam String token){
        var careResponse = cartService.getCart(token);
        return ResponseEntity.ok(careResponse);
    }
    @PutMapping("/cart")
    public ResponseEntity<CartResponseDto> changeAmount(@RequestParam String token, CartItemToChangeAmountRequestDto requestDto){
        var cartResponse = cartService.changeAmount(token, requestDto);
        return ResponseEntity.ok(cartResponse);
    }
    @PutMapping("/cart/{id}")
    public ResponseEntity<CartResponseDto> addToCart(@RequestParam String token, @RequestParam Long id, SizeEnum size){
        var cartResponse = cartService.addToCart(token,id,size);
        return ResponseEntity.ok(cartResponse);
    }
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<CartResponseDto> removeFromCart(@RequestParam String token,@RequestParam Long id){
        var cartResponse = cartService.removeFromCart(token,id);
        return ResponseEntity.ok(cartResponse);
    }


}
