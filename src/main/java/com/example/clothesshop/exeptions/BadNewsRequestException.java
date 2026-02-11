package com.example.clothesshop.exeptions;

public class BadNewsRequestException extends RuntimeException {
    public BadNewsRequestException(String message) {
        super(message);
    }
}
