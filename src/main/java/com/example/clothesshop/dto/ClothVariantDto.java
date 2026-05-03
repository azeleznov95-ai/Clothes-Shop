package com.example.clothesshop.dto;

import com.example.clothesshop.enums.SizeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothVariantDto {
    private SizeEnum size;
    private Integer remainingAmount;
}
