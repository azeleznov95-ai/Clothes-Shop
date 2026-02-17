package com.example.clothesshop.exeptions;

public class ClothNotFoundException extends RuntimeException {
    public ClothNotFoundException(String message) {
        super(message);
    }
}
