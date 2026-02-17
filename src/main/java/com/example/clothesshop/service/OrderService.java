package com.example.clothesshop.service;

import com.example.clothesshop.enums.CartStatus;
import com.example.clothesshop.enums.OrderStatusEnum;
import com.example.clothesshop.exeptions.UserNotFoundException;
import com.example.clothesshop.mapper.OrderMapper;
import com.example.clothesshop.model.*;
import com.example.clothesshop.repository.CartRepository;
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
    private final OrderMapper orderMapper;
    private final JWToken jwToken;
    private final UserRepository usersRepository;
    private final CartRepository cartRepository;

    OrderService(OrderRepository orderRepository,
                 OrderMapper orderMapper,
                 JWToken jWToken,
                 UserRepository usersRepository,
                 CartRepository cartRepository, CartRepository cartRepository1){
        this.orderRepository = orderRepository;
        this.orderMapper= orderMapper;
        this.jwToken = jWToken;

        this.usersRepository = usersRepository;
        this.cartRepository = cartRepository1;
    }
    @Transactional
    public Orders setOrderFromCart (String token, Cart cart){
        Orders orders = new Orders();
        cart.setCartStatus(CartStatus.ORDERED);
        cartRepository.save(cart);
        Long userId = jwToken.extractUserId(token);
        Optional<Users> userOpt = usersRepository.findUserById(userId);
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("User is not found");
        }

        orders.setUser(userOpt.get());
        orders.setOrderStatus(OrderStatusEnum.CREATED);
        var cartItems = cart.getCartItems();
        List<OrderItem> orderItems= new ArrayList<>();
        for (var cartItem: cartItems){
            var orderItem = orderMapper.cartItemToOrderItem(cartItem);
            orderItem.setOrder(orders);
            orderItems.add(orderItem);
        }
        orders.setOrderItems(orderItems);
        orderRepository.save(orders);
        return orders;

    }
}
