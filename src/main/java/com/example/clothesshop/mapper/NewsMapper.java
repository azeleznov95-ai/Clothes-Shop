package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.NewsRequestDto;
import com.example.clothesshop.dto.NewsResponseDto;
import com.example.clothesshop.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {
    public News toEntity(NewsRequestDto req){
        News entity = new News();
        entity.setDescription(req.getDescription());
        entity.setName(req.getName());
        entity.setImages(req.getImages());
        return entity;
    }
    public NewsResponseDto toResponse(News entity){
        NewsResponseDto response = new NewsResponseDto();
        response.setDescription(entity.getDescription());
        response.setImages(entity.getImages());
        response.setName(entity.getName());
        return response;
    }
}
