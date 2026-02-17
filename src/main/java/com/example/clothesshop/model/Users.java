package com.example.clothesshop.model;

import com.example.clothesshop.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Users {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false)
        private String passwordHash;
        @Column(nullable = false)
        //todo: tgid tgusername при регистрации указывать
        private String telegramUsername="0";
        @Column()
        private  Long telegramId;
        @Column(nullable = false)
        private RolesEnum role= RolesEnum.User;


}
