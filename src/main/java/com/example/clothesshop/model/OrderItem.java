package com.example.clothesshop.model;

import com.example.clothesshop.enums.SizeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer priceSnapshot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Clothes cloth;

    @Column
    private Integer amount;
    @Column
    private String imageUrl;
    @ManyToOne
    @JoinColumn()
    private Orders order;
    @Column
    @Enumerated(EnumType.STRING)
    private SizeEnum size;



}
