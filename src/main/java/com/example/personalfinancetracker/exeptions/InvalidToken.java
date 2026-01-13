package com.example.personalfinancetracker.exeptions;

public class InvalidToken extends RuntimeException{
    public InvalidToken(String message){
        super(message);
    }
}
