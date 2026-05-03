package com.example.clothesshop.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private Integer price;
    @Column()
    private String description;
    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClothVariant> setClothesVariants;
    @Column()
    private String imageUrl;
    @Column()
    private boolean active=true;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void setCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

}
