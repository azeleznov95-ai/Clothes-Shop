package com.example.clothesshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false,unique = true)
    @NotBlank
    private String slug;
    @Column
    private LocalDateTime createdAt;
    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

}
