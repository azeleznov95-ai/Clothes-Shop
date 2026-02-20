package com.example.clothesshop.model;

import com.example.clothesshop.enums.SizeEnum;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable( joinColumns = @JoinColumn())
    @Enumerated(EnumType.STRING)
    @Column()
    private Set<SizeEnum> setSize;
    @Column()
    private String imageUrl;
    @Column()
    private boolean active=true;
    @Column(nullable = false)
    private Integer remainingAmount=0;
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
