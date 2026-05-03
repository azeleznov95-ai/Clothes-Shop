package com.example.clothesshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClothesAddRequestDto {

    private String name;
    private Integer price;
    private String description;
    private Set<ClothVariantDto> variants;
    private String imageUrl;
    private Integer remainingAmount;
    private boolean active=true;
    private String categorySlug;


}
