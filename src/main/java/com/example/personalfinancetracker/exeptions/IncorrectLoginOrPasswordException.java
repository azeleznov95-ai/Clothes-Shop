package com.example.personalfinancetracker.exeptions;

public class IncorrectLoginOrPasswordException extends Exception{
    public IncorrectLoginOrPasswordException(String message){
        super(message);
    }
}
