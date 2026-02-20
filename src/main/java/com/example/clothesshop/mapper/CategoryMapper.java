package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CategoryResponseDto;
import com.example.clothesshop.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toResponse(Category entity){
        var response = new CategoryResponseDto();
        response.setName(entity.getName());
        response.setSlug(entity.getSlug());
        return response;
    }
}
