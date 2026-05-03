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
        this.clothesRepository = clothesRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }
    public ClothesResponseDto create(ClothesAddRequestDto requestDto){
        if (requestDto.getName() == null || requestDto.getName().isBlank()){
            throw new BadClothesRequestException("Name is null or blank");
        }
        if (requestDto.getPrice() == null){
            throw new BadClothesRequestException("Price is null");
        }
        if (requestDto.getPrice() <= 0){
            throw new BadClothesRequestException("Price must be more than zero");
        }
        if (requestDto.getRemainingAmount() == null){
            throw new BadClothesRequestException("remaining amount is null");
        }
        if (requestDto.getRemainingAmount() < 0){
            throw new BadClothesRequestException("remaining amount must be more than zero or equal");
        }
        var slug = requestDto.getCategorySlug();
        if (slug == null || slug.isBlank()){
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
        var entity = mapper.toEntity(requestDto, category);
        var saved = clothesRepository.save(entity);
        return mapper.toResponse(saved);
    }
    public ClothesResponseDto getById(Long id){
        Optional<Clothes> clothesOpt = clothesRepository.findClothesById(id);
        if (clothesOpt.isEmpty()){
            throw new BadClothesRequestException("Cloth doesn't exist");

        }
        Clothes clothes = clothesOpt.get();
        return mapper.toResponse(clothes);
    }
    public List<ClothesResponseDto> getAll(){
        List<Clothes> clothesList =  clothesRepository.findAll();
        List<ClothesResponseDto> listResponse = new ArrayList<>();
        for (Clothes clothes: clothesList){
            listResponse.add(mapper.toResponse(clothes));
        }
        return listResponse;
    }
    public ClothesResponseDto changeAmount(ClothChangeAmountRequestDto requestDto){
        var delta = requestDto.getDelta();

        var clothesOpt = clothesRepository.findClothesById(requestDto.getId());
        if (clothesOpt.isEmpty()){
            throw new BadClothesRequestException("Id doesn't match any exising cloth");

        }
        var clothSize = requestDto.getSize();
        var clothes = clothesOpt.get();
        var clothesVariants = clothes.getSetClothesVariants();
        var neededClothOpt = clothesVariants.stream().filter(variant -> variant.getSize().equals(clothSize)).findFirst();
        if (neededClothOpt.isEmpty()){
            throw new BadClothesRequestException("No size in variants to change");
        }
        var clothesVariant = neededClothOpt.get();


        if (clothesVariant.getRemainingAmount() + delta < 0){
            throw new BadClothesRequestException("Resulting remainingAmount cannot  negative");
        }
        if (delta == 0){
            throw new BadClothesRequestException("Delta is zero");
        }

        clothesVariant.setRemainingAmount(clothesVariant.getRemainingAmount() + delta);
        clothesVariants.removeIf(variant -> variant.getSize().equals(clothSize));
        clothesVariants.add(clothesVariant);

        clothes.setSetClothesVariants(clothesVariants);
        clothesRepository.save(clothes);
        return mapper.toResponse(clothes);
    }
    public ClothesResponseDto hide(Long id){
        var clothesOpt = clothesRepository.findClothesById(id);
        if (clothesOpt.isEmpty()){
            throw new ClothNotFoundException("Cloth is not found");
        }
        var clothes = clothesOpt.get();
        clothes.setActive(false);
        clothesRepository.save(clothes);
        return mapper.toResponse(clothes);
    }
    public ClothesResponseDto show(Long id){
        var clothesOpt = clothesRepository.findClothesById(id);
        if (clothesOpt.isEmpty()){
            throw new ClothNotFoundException("Cloth is not found");
        }
        var clothes = clothesOpt.get();
        clothes.setActive(true);
        clothesRepository.save(clothes);
        return mapper.toResponse(clothes);
    }

    public List<ClothesResponseDto> filterClothesByCategory(String slug) {
        var clothesList = clothesRepository.findAllClothesByCategorySlugAndActiveIsTrue(slug);
        List<ClothesResponseDto> listResponse = new ArrayList<>();
        for (Clothes clothes: clothesList){
            var response = mapper.toResponse(clothes);
            listResponse.add(response);
        }
        return listResponse;
    }
}
