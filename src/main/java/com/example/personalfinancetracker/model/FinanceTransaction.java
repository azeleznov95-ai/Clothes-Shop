package com.example.personalfinancetracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "FinanceTransactions")
@Getter
@Setter
public class FinanceTransaction {
    @Column(unique = true, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, name = "amount",nullable = false)
    private int amount;
    @Column(unique = true,nullable = false,name = "user")
    private @ManyToOne User user;
    @JoinColumn(unique = true, name = "category",nullable = true)
    @ManyToOne private Category category;
    @JoinColumn(unique = true,nullable = true)
    @Column(unique = false,nullable = true,name="description")
    String description;
    @Column(unique = false,nullable = false,name = "createdAt")
    private LocalDateTime createdAt;
}
