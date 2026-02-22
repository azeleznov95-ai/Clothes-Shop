package com.example.clothesshop.controller;

import com.example.clothesshop.dto.ClothChangeAmountRequestDto;
import com.example.clothesshop.dto.ClothesAddRequestDto;
import com.example.clothesshop.dto.ClothesResponseDto;
import com.example.clothesshop.service.ClothesService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
public class ClothesController {
    private final ClothesService clothesService;
    ClothesController(ClothesService clothesService){
        this.clothesService=clothesService;
    }
    @PostMapping("/admin")
    public ResponseEntity<ClothesResponseDto> addClothes(@RequestBody @Valid ClothesAddRequestDto req){
        ClothesResponseDto response =clothesService.create(req);
        return ResponseEntity.status(201).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClothesResponseDto> getCloth(@PathVariable Long id){
       var response = clothesService.getById(id);
       return ResponseEntity.status(200).body(response);
    }
    @GetMapping()
    public  ResponseEntity<List<ClothesResponseDto>> getList(){
        return ResponseEntity.status(200).body(clothesService.getList());
    }
    @PatchMapping("/admin")
    @Transactional
    public  ResponseEntity<ClothesResponseDto> updateAmount(@RequestBody @Valid ClothChangeAmountRequestDto changeAmountRequest){
        var changedResponse =clothesService.changeAmount(changeAmountRequest);
        return ResponseEntity.status(200).body(changedResponse);
    }
    @PatchMapping("/admin/{id}/hide")
    public ResponseEntity<ClothesResponseDto> hideCloth(@PathVariable Long id){
        var clothesResponse = clothesService.hide(id);
        return ResponseEntity.ok(clothesResponse);
    }
    @PatchMapping("/admin/{id}/show")
    public ResponseEntity<ClothesResponseDto> showCloth(@PathVariable Long id){
        var clothesResponse = clothesService.show(id);
        return ResponseEntity.ok(clothesResponse);
    }
    @GetMapping("/by-category/{slug}")
    public ResponseEntity<List<ClothesResponseDto>> filterClothByCategory(@PathVariable String slug){
        var clothesResponse = clothesService.filerClothByCategory(slug);
        return ResponseEntity.ok(clothesResponse);


    }
}
