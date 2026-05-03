package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.ClothVariantDto;
import com.example.clothesshop.dto.ClothesAddRequestDto;
import com.example.clothesshop.dto.ClothesResponseDto;
import com.example.clothesshop.enums.SizeEnum;
import com.example.clothesshop.model.Category;
import com.example.clothesshop.model.ClothVariant;
import com.example.clothesshop.model.Clothes;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component()
public class ClothesMapper {
    public  Clothes toEntity(ClothesAddRequestDto req, Category category){
        Clothes entity  = new Clothes();
        entity.setName(req.getName());
        entity.setActive(req.isActive());
        entity.setPrice(req.getPrice());
        entity.setImageUrl(req.getImageUrl());
        entity.setCategory(category);
        entity.setDescription(req.getDescription());
        entity.setSetClothesVariants(mapVariants(req, entity));

        return entity;
    }
    public  ClothesResponseDto toResponse(Clothes entity){
        ClothesResponseDto resp = new ClothesResponseDto();
        var clothVariants = entity.getSetClothesVariants();
        Set<SizeEnum> sizes = new HashSet<>();
        for(ClothVariant variant : clothVariants){
            sizes.add(variant.getSize());

        }
        resp.setSizes(sizes);
        resp.setDescription(entity.getDescription());
        resp.setId(entity.getId());
        resp.setPrice(entity.getPrice());
        resp.setCreatedAt(entity.getCreatedAt());
        resp.setImageUrl(entity.getImageUrl());
        resp.setName(entity.getName());
        resp.setActive(entity.isActive());
        resp.setCategoryName(entity.getCategory().getName());
        resp.setCategorySlug(entity.getCategory().getSlug());
        return resp;
    }

    private Set<ClothVariant> mapVariants(ClothesAddRequestDto req, Clothes entity) {
        var variants = req.getVariants();
        if (variants == null || variants.isEmpty()) {
            return new HashSet<>();
        }
        Set<ClothVariant> mapped = new HashSet<>();
        for (ClothVariantDto dto : variants) {
            ClothVariant variant = new ClothVariant();
            variant.setClothes(entity);
            variant.setSize(dto.getSize());
            variant.setRemainingAmount(dto.getRemainingAmount());
            mapped.add(variant);
        }
        return mapped;
    }
}
