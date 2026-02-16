package com.example.clothesshop.repository;

import com.example.clothesshop.model.Cart;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findCartByUserId(Long id);


}
