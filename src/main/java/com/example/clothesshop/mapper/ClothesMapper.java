package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.ClothesAddRequestDto;
import com.example.clothesshop.dto.ClothesResponseDto;
import com.example.clothesshop.model.Category;
import com.example.clothesshop.model.Clothes;
import org.springframework.stereotype.Component;

@Component()
public class ClothesMapper {
    public  Clothes toEntity(ClothesAddRequestDto req, Category category){
        Clothes entity  = new Clothes();
        entity.setName(req.getName());
        entity.setActive(req.isActive());
        entity.setPrice(req.getPrice());
        entity.setImageUrl(req.getImageUrl());
        entity.setSetSize(req.getSizes());
        entity.setRemainingAmount(req.getRemainingAmount());
        entity.setDescription(req.getDescription());
        entity.setCategory(category);

        return entity;
    }
    public  ClothesResponseDto toResponse(Clothes entity){
        ClothesResponseDto resp = new ClothesResponseDto();
        resp.setSizes(entity.getSetSize());
        resp.setDescription(entity.getDescription());
        resp.setId(entity.getId());
        resp.setPrice(entity.getPrice());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setImageUrl(entity.getImageUrl());
        resp.setName(entity.getName());
        resp.setActive(entity.isActive());
        resp.setRemainingAmount(entity.getRemainingAmount());
        resp.setCategoryName(entity.getCategory().getName());
        return resp;
    }
}
