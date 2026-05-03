package com.example.clothesshop.model;

import com.example.clothesshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table
@Getter
@Setter
public class ClothVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn()
    private Clothes clothes;
    @Enumerated(EnumType.STRING)
    private SizeEnum size;
    @Column
    private LocalDateTime createdAt;
    @PrePersist
    public void setTimeStamp(){
        this.createdAt = LocalDateTime.now();
    }
    private Integer remainingAmount;

}
