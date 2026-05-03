package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CartItemToChangeAmountRequestDto;
import com.example.clothesshop.dto.CartResponseDto;
import com.example.clothesshop.enums.SizeEnum;
import com.example.clothesshop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(@CookieValue(value = "access_token", required = false) String token){
        var cartResponse = cartService.getCart(token);
        return ResponseEntity.ok(cartResponse);
    }

    @PutMapping
    public ResponseEntity<CartResponseDto> changeAmount(
            @CookieValue(value = "access_token", required = false) String token,
            @RequestBody CartItemToChangeAmountRequestDto requestDto
    ){
        var cartResponse = cartService.changeAmount(token, requestDto);
        return ResponseEntity.ok(cartResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDto> addToCart(
            @CookieValue(value = "access_token", required = false) String token,
            @PathVariable Long id,
            @RequestParam SizeEnum size
    ){
        var cartResponse = cartService.addToCart(token, id, size);
        return ResponseEntity.ok(cartResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponseDto> removeFromCart(
            @CookieValue(value = "access_token", required = false) String token,
            @PathVariable Long id
    ){
        var cartResponse = cartService.removeFromCart(token, id);
        return ResponseEntity.ok(cartResponse);
    }
}
