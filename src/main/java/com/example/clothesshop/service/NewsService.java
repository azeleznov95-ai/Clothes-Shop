package com.example.clothesshop.service;

import com.example.clothesshop.dto.NewsRequestDto;
import com.example.clothesshop.dto.NewsResponseDto;
import com.example.clothesshop.exeptions.BadNewsRequestException;

import com.example.clothesshop.exeptions.NewsNotFoundException;
import com.example.clothesshop.mapper.NewsMapper;
import com.example.clothesshop.model.News;
import com.example.clothesshop.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper mapper;
    NewsService(NewsRepository newsRepository,NewsMapper mapper){
        this.newsRepository= newsRepository;
        this.mapper = mapper;
    }
    public NewsResponseDto putNews(NewsRequestDto requestDto){
            if (requestDto.getName().isBlank()){
                throw new BadNewsRequestException("Name is invalid");
            }
            if (requestDto.getDescription().isBlank()){
            throw new BadNewsRequestException("Description is invalid");
            }
            if (requestDto.getImages().isEmpty()){
            throw new BadNewsRequestException("There are no images ");
            }
        Optional<News> oldNews = newsRepository.getNewsByIsActive(true);

        News updatedNews = mapper.toEntity(requestDto);
        newsRepository.save(updatedNews);
        return mapper.toResponse(updatedNews);
    }
    public List<NewsResponseDto> getNews(){
        var activeNews = newsRepository.findAllByIsActive(true);
        if(activeNews.isEmpty()){
            throw new NewsNotFoundException("No news are available");
        }
        if(activeNews.size()>3){
            activeNews = activeNews.stream()
                    .sorted(Comparator
                    .comparing(News::getCreatedAt).reversed())
                    .limit(3)
                    .toList();

        }
        var responseNews = new ArrayList<NewsResponseDto>();
        for(News news: activeNews){
            responseNews.add(mapper.toResponse(news));
        }
        return responseNews;

    }

    public List<NewsResponseDto> getNewsList(){
        List<News> newsList = newsRepository.findAll();
        List<NewsResponseDto> responseList = new ArrayList<>();
        if (newsList.isEmpty()){
            throw new BadNewsRequestException("No news yet");
        }
        for(News news: newsList){
            responseList.add(mapper.toResponse(news));
        }
        return responseList;
    }
}
