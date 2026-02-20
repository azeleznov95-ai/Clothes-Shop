package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CategoryAddRequestDto;
import com.example.clothesshop.dto.CategoryResponseDto;
import com.example.clothesshop.dto.RefactorCategoryDto;
import com.example.clothesshop.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        var categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
    @PostMapping("/admin")
    public ResponseEntity<CategoryResponseDto> putCategory(@RequestBody CategoryAddRequestDto request){
        var category = categoryService.putCategory(request);
        return ResponseEntity.status(201).body(category);

    }
    @GetMapping("/{slug}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable String slug){
        var category = categoryService.findBySlug(slug);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/admin/{slug}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String slug){
        categoryService.deleteCategory(slug);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/admin/{slug}")
    public ResponseEntity<CategoryResponseDto> refactorCategory(
            @PathVariable String slug,
            @RequestBody RefactorCategoryDto request
    ){
        var updatedCategory = categoryService.refactorCategory(slug, request);
        return ResponseEntity.ok(updatedCategory);
    }




}
