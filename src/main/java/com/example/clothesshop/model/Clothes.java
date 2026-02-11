package com.example.clothesshop.model;

import com.example.clothesshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = true)
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable( joinColumns = @JoinColumn())
    @Enumerated(EnumType.STRING)
    @Column()
    Set<SizeEnum> setSize;
    @Column()
    String imageUrl;
    @Column()
    boolean active=true;
    @Column(nullable = false)
    private Integer remainingAmount=0;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void setCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
