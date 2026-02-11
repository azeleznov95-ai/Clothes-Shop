package com.example.clothesshop.exeptions;

public class BadClothesRequestException extends RuntimeException {
    public BadClothesRequestException(String message) {
        super(message);
    }
}
