package com.example.clothesshop.controller;

import com.example.clothesshop.dto.NewsRequestDto;
import com.example.clothesshop.dto.NewsResponseDto;
import com.example.clothesshop.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    NewsController(NewsService newsService){
        this.newsService = newsService;
    }
@GetMapping
    public ResponseEntity<List<NewsResponseDto>> get3NewsForUser(){
        var news = newsService.getNews();
        return ResponseEntity.ok(news);
    }

@PutMapping
    public ResponseEntity<NewsResponseDto> putNews(@RequestBody NewsRequestDto newsRequestDto){
        var response = newsService.putNews(newsRequestDto);
        return ResponseEntity.ok(response);
}
@GetMapping( )
        public ResponseEntity<List<NewsResponseDto>> getNewsList(){
        var response = newsService.getNewsList();
        return ResponseEntity.ok(response);
    }
}
