package com.example.clothesshop.repository;

import com.example.clothesshop.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes,Long> {

    List<Clothes> findAllClothesByCategorySlug(String slug);

    Optional<Clothes> findClothesById(Long id);

}
