package com.example.clothesshop.service;

import com.example.clothesshop.dto.OrderFromOneClothDto;
import com.example.clothesshop.dto.OrderResponseDto;
import com.example.clothesshop.enums.CartStatusEnum;
import com.example.clothesshop.enums.OrderStatusEnum;

import com.example.clothesshop.exeptions.BadClothesRequestException;
import com.example.clothesshop.exeptions.CartNotFoundException;
import com.example.clothesshop.exeptions.UserNotFoundException;

import com.example.clothesshop.mapper.OrderItemMapper;
import com.example.clothesshop.mapper.OrderMapper;
import com.example.clothesshop.model.*;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.repository.ClothesRepository;
import com.example.clothesshop.repository.OrderRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.security.JWToken;
import jakarta.transaction.Transactional;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final JWToken jwToken;
    private final UserRepository usersRepository;
    private final CartRepository cartRepository;
    private final ClothesRepository clothesRepository;


    OrderService(OrderRepository orderRepository,
                 OrderItemMapper orderItemMapper,
                 JWToken jWToken,
                 UserRepository usersRepository,
                 CartRepository cartRepository,
                 ClothesRepository clothesRepository,
                 OrderMapper orderMapper){
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderItemMapper = orderItemMapper;
        this.jwToken = jWToken;

        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository;

        this.clothesRepository = clothesRepository;
    }
    @Transactional
    public OrderResponseDto setOrderFromCart (String token){
        var userId = jwToken.extractUserId(token);

        Orders orders = new Orders();
        var cartOpt = cartRepository.findCartByUserId(userId);
        if (cartOpt.isEmpty()){
            throw new CartNotFoundException("Cart is empty");
        }
        var cart = cartOpt.get();
        cart.setCartStatus(CartStatusEnum.ORDERED);
        cartRepository.save(cart);

        Optional<Users> userOpt = usersRepository.findUserById(userId);
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("User is not found");
        }

        orders.setUser(userOpt.get());
        orders.setOrderStatus(OrderStatusEnum.CREATED);
        var cartItems = cart.getCartItems();
        List<OrderItem> orderItems= new ArrayList<>();
        for (var cartItem: cartItems){
            var orderItem = orderItemMapper.cartItemToOrderItem(cartItem);
            orderItem.setOrder(orders);
            orderItems.add(orderItem);
        }
        orders.setOrderItems(orderItems);
        orderRepository.save(orders);
        return orderMapper.toResponse(orders,userId);

    }
    public OrderResponseDto setOrderFromOneCloth(String token, OrderFromOneClothDto request){
        var itemId = request.getItemId();
        var size = request.getSize();
        var amount = request.getAmount();
        var clothesOpt = clothesRepository.findClothesById(itemId);
        if (clothesOpt.isEmpty()){
            throw new BadClothesRequestException("Cloth doesn't exist");
        }
        var cloth = clothesOpt.get();
        Orders order = new Orders();
        Long userId = jwToken.extractUserId(token);
        Optional<Users> userOpt = usersRepository.findUserById(userId);
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("User is not found");
        }
        order.setUser(userOpt.get());
        order.setOrderStatus(OrderStatusEnum.CREATED);
        var cartItem = new CartItem();
        cartItem.setCloth(cloth);
        cartItem.setSize(size);
        cartItem.setAmount(amount);
        cartItem.setImageUrl(cloth.getImageUrl());
        cartItem.setPriceSnapshot(cloth.getPrice());
        var orderItem = orderItemMapper.cartItemToOrderItem(cartItem);
        orderItem.setOrder(order);
        var orderItems = new ArrayList<OrderItem>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return orderMapper.toResponse(order,userId);

    }
}
