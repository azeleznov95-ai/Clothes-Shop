package com.example.clothesshop.dto;

import com.example.clothesshop.enums.SizeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClothesAddRequestDto {
    private String name;
    private Integer price;
    private String description;
    private Set<SizeEnum> sizes;
    private String imageUrl;
    private Integer remainingAmount;
    private boolean active=true;

}
