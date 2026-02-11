package com.example.clothesshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(nullable = false)
    List<String> images;
    @Column(nullable = false,unique = true)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    LocalDateTime createdAt;
    @Column(nullable = false)
    Boolean isActive= true;
    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }
}
