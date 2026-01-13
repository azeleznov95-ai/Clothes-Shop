package com.example.personalfinancetracker.exeptions;

public class IncorrectLoginOrPasswordException extends RuntimeException{
    public IncorrectLoginOrPasswordException(String message){
        super(message);
    }
}
