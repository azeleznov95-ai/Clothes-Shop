package com.example.personalfinancetracker.exeptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message){
        super(message);
    }
}
