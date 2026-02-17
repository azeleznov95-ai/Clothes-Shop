package com.example.clothesshop.service;

import com.example.clothesshop.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository= paymentRepository;
    }

}
