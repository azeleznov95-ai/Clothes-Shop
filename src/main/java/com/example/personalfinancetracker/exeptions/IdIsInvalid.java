package com.example.personalfinancetracker.exeptions;

public class IdIsInvalid extends RuntimeException{
    public IdIsInvalid(String message){
        super(message);
    }
}
