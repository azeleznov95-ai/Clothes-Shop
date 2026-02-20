package com.example.clothesshop.model;

import com.example.clothesshop.enums.CartStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long userId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn()
    private List<CartItem> cartItems;
    @Column
    CartStatusEnum cartStatus = CartStatusEnum.EMPTY;


}
