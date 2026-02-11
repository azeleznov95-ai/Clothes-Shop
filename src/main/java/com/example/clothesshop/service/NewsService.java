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
        if(oldNews.isPresent()) {
            News oldEntity = oldNews.get();
            oldEntity.setIsActive(false);
            newsRepository.save(oldEntity);


        }
        News updatedNews = mapper.toEntity(requestDto);
        newsRepository.save(updatedNews);
        return mapper.toResponse(updatedNews);
    }
    public NewsResponseDto getNews(){
        Optional <News> newsOpt =  newsRepository.getNewsByIsActive(true);
        if (newsOpt.isEmpty()){
            throw new NewsNotFoundException("No active news available");
        }
        var newsEntity = newsOpt.get();
        return mapper.toResponse(newsEntity);
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
