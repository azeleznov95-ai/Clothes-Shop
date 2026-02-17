package com.example.clothesshop.model;

import com.example.clothesshop.enums.Currency;
import com.example.clothesshop.enums.PaymentStatus;
import com.example.clothesshop.enums.Providers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long orderId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Providers provider;
    @Column(nullable = false)
    private UUID externalPaymentId;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        createdAt = LocalDateTime.now();
    }
}
