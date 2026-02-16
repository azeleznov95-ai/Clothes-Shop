package com.example.clothesshop.controller;

import com.example.clothesshop.dto.NewsRequestDto;
import com.example.clothesshop.dto.NewsResponseDto;
import com.example.clothesshop.service.NewsService;
import jakarta.transaction.Transactional;
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
@Transactional
@PutMapping("/admin/put")
    public ResponseEntity<NewsResponseDto> putNews(@RequestBody NewsRequestDto newsRequestDto){
        var response = newsService.putNews(newsRequestDto);
        return ResponseEntity.ok(response);
}
//todo: админский доступ
@GetMapping("admin/latest")
        public ResponseEntity<List<NewsResponseDto>> getNewsList(){
        var response = newsService.getNewsList();
        return ResponseEntity.ok(response);
    }
}
