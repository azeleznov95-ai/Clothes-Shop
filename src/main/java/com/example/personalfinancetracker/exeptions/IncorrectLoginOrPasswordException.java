package com.example.personalfinancetracker.exeptions;

public class incorrectLoginOrPasswordException extends RuntimeException{
    public incorrectLoginOrPasswordException(String message){
        super(message);
    }
}
