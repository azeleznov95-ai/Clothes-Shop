package com.example.clothesshop.service;


import com.example.clothesshop.dto.ClothChangeAmountRequestDto;
import com.example.clothesshop.dto.ClothesAddRequestDto;
import com.example.clothesshop.dto.ClothesResponseDto;
import com.example.clothesshop.exeptions.BadClothesRequestException;
import com.example.clothesshop.exeptions.CategoryNotFoundException;
import com.example.clothesshop.exeptions.ClothNotFoundException;
import com.example.clothesshop.mapper.ClothesMapper;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.repository.ClothesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class ClothesService {
private final ClothesRepository clothesRepository;
private final ClothesMapper mapper;
    private final CategoryRepository categoryRepository;

    ClothesService(ClothesRepository clothesRepository, ClothesMapper mapper, CategoryRepository categoryRepository){
        this.clothesRepository=clothesRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }
    public ClothesResponseDto create(ClothesAddRequestDto requestDto){
        if (requestDto.getName()==null|| requestDto.getName().isBlank()){
            throw new BadClothesRequestException("Name is null or blank");
        }
        if (requestDto.getPrice() == null){
            throw new BadClothesRequestException("Price is null");
        }
        if (requestDto.getPrice()<=0){
            throw new BadClothesRequestException("Price must be more than zero");
        }
        if (requestDto.getRemainingAmount()==null){
            throw new BadClothesRequestException("remaining amount is null");
        }
        if (requestDto.getRemainingAmount()<0){
            throw new BadClothesRequestException("remaining amount must be more than zero or equal");
        }
        var slug = requestDto.getCategorySlug();
        if (slug == null||slug.isBlank()){
            throw new CategoryNotFoundException("Slug is empty");
        }
        if (!categoryRepository.existsBySlug(slug)){
            throw new CategoryNotFoundException("No category with this slug");
        }


        var categoryOpt = categoryRepository.findBySlug(slug);
        if (categoryOpt.isEmpty()){
            throw new CategoryNotFoundException("No available category with this slug");
        }
        var category = categoryOpt.get();
        var entity = mapper.toEntity(requestDto,category);
        var saved = clothesRepository.save(entity);
        return mapper.toResponse(saved);
    }
    public ClothesResponseDto getById(Long id){
        Optional<Clothes> clothOpt= clothesRepository.findClothesById(id);
    if (clothOpt.isEmpty()){
        throw new BadClothesRequestException("Cloth doesn't exist");

    }
    Clothes cloth = clothOpt.get();
        return mapper.toResponse(cloth);
    }
    public List<ClothesResponseDto> getList(){
        List<Clothes>ListClothes =  clothesRepository.findAll();
        List<ClothesResponseDto> listResponse = new ArrayList<>();
        for (Clothes cloth: ListClothes){
            listResponse.add(mapper.toResponse(cloth));
        }
        return listResponse;
    }
    public ClothesResponseDto changeAmount(ClothChangeAmountRequestDto requestDto){
        var delta = requestDto.getDelta();

        var clothOpt = clothesRepository.findClothesById(requestDto.getId());
        if (clothOpt.isEmpty()){
            throw new BadClothesRequestException("Id doesn't match any exising cloth");

        }

        var cloth = clothOpt.get();
        if (cloth.getRemainingAmount() + delta < 0){
            throw new BadClothesRequestException("Resulting remainingAmount cannot  negative");
        }
        if (delta ==0){
            throw new BadClothesRequestException("delta is zero");
        }

        cloth.setRemainingAmount(cloth.getRemainingAmount()+delta);
        clothesRepository.save(cloth);
        return mapper.toResponse(cloth);
    }
    public ClothesResponseDto hide(Long id){
        var clothOpt = clothesRepository.findClothesById(id);
        if (clothOpt.isEmpty()){
            throw new ClothNotFoundException("Cloth is not found");
        }
        var cloth = clothOpt.get();
        cloth.setActive(false);
        clothesRepository.save(cloth);
        return mapper.toResponse(cloth);
    }
    public ClothesResponseDto show(Long id){
        var clothOpt = clothesRepository.findClothesById(id);
        if (clothOpt.isEmpty()){
            throw new ClothNotFoundException("Cloth is not found");
        }
        var cloth = clothOpt.get();
        cloth.setActive(true);
        clothesRepository.save(cloth);
        return mapper.toResponse(cloth);
    }

    public List<ClothesResponseDto> filerClothByCategory(String slug) {
        var listClothes = clothesRepository.findAllClothesByCategorySlug(slug);
        List<ClothesResponseDto> listResponse = new ArrayList<>();
        for (Clothes cloth: listClothes){
            var response = mapper.toResponse(cloth);
            listResponse.add(response);
        }
        return listResponse;
    }
}

