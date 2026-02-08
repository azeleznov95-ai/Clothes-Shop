package com.example.clothesshop.service;

import com.example.clothesshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;
    OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public void setOrderFromCart (){};
}
