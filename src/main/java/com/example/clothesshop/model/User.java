package com.example.clothesshop.model;

import com.example.clothesshop.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false)
        private String passwordHash;
        @Column(nullable = false,unique = true)
        private String telegramUsername="0";
        @Column(nullable = true,unique = true)
        private  Long telegramId;
        @Column(nullable = false)
        private RolesEnum role= RolesEnum.User;


}
