package com.example.clothesshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer priceSnapshot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Clothes cloth;
    @Column
    private LocalDateTime addedAt;


    @PrePersist
    public void setAddedAt(){
        addedAt = LocalDateTime.now();
    }
    @Column
    private Integer amount;
    @Column
    private String imageUrl;




}
