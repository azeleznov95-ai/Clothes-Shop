package com.example.clothesshop.service;

import com.example.clothesshop.dto.CategoryAddRequestDto;
import com.example.clothesshop.dto.CategoryResponseDto;
import com.example.clothesshop.dto.RefactorCategoryDto;
import com.example.clothesshop.exeptions.CategoryNotFoundException;
import com.example.clothesshop.exeptions.ConflictException;
import com.example.clothesshop.exeptions.EmptyCategoryException;
import com.example.clothesshop.mapper.CategoryMapper;
import com.example.clothesshop.model.Category;
import com.example.clothesshop.model.Clothes;
import com.example.clothesshop.repository.CategoryRepository;
import com.example.clothesshop.repository.ClothesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ClothesService clothesService;
    private final ClothesRepository clothesRepository;

    CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ClothesService clothesService, ClothesRepository clothesRepository){
        this.categoryRepository = categoryRepository;
        this. categoryMapper = categoryMapper;
        this.clothesService = clothesService;
        this.clothesRepository = clothesRepository;
    }
    public List<CategoryResponseDto> getCategories(){
        var categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseList = new ArrayList<>();

        for (Category category: categories){
            var response = categoryMapper.toResponse(category);
            categoryResponseList.add(response);

        }
        return categoryResponseList;

    }
    public CategoryResponseDto putCategory(CategoryAddRequestDto request){
        if (request.getName() == null || request.getName().isBlank()) {
            throw new EmptyCategoryException("Name is null or blank");
        }
        if (request.getSlug() == null || request.getSlug().isBlank()) {
            throw new EmptyCategoryException("Slug is null or blank");
        }
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new ConflictException("Category with this slug already exists");
        }
        Category category = new Category();
        category.setSlug(request.getSlug());
        category.setName(request.getName());
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);

    }
    public CategoryResponseDto findBySlug(String slug){
        var categoryOpt = categoryRepository.findBySlug(slug);
        if (categoryOpt.isEmpty()){
            throw new CategoryNotFoundException("No any category with this slug");

        }
        var category = categoryOpt.get();
        return categoryMapper.toResponse(category);
    }
    public void deleteCategory(String slug){
        var categoryOpt = categoryRepository.findBySlug(slug);
        if (categoryOpt.isEmpty()){
            throw new CategoryNotFoundException("Nothing to delete");
        }
        if (!clothesService.filerClothByCategory(slug).isEmpty()){
            throw new ConflictException("Category can't be delete cause there is clothes that linked on it");
        }
        var category = categoryOpt.get();
        categoryRepository.delete(category);

    }

    public CategoryResponseDto refactorCategory(String slug, RefactorCategoryDto request) {
        var categoryOpt = categoryRepository.findBySlug(slug);
        if (categoryOpt.isEmpty()) {
            throw new CategoryNotFoundException("Category is not found");
        }
        var category = categoryOpt.get();

        boolean updated = false;

        if (request.getName() != null) {
            if (request.getName().isBlank()) {
                throw new EmptyCategoryException("Name is null or blank");
            }
            category.setName(request.getName());
            updated = true;
        }

        if (request.getSlug() != null) {
            if (request.getSlug().isBlank()) {
                throw new EmptyCategoryException("Slug is null or blank");
            }
            if (!request.getSlug().equals(category.getSlug()) && categoryRepository.existsBySlug(request.getSlug())) {
                throw new ConflictException("Category with this slug already exists");
            }
            category.setSlug(request.getSlug());
            updated = true;
        }

        if (!updated) {
            throw new EmptyCategoryException("Nothing to update");
        }
        var listToUpdate = clothesRepository.findAllClothesByCategorySlugAndActiveIsTrue(slug);
        if (!listToUpdate.isEmpty()){
        for(Clothes cloth : listToUpdate) {
            cloth.setCategory(category);
            categoryRepository.save(category);
        }
        }
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }
}
