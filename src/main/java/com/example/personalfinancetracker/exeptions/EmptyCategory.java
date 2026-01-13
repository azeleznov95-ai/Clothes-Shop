package com.example.personalfinancetracker.exeptions;

public class EmptyCategory extends RuntimeException{
    public EmptyCategory(String message){
        super(message);
    }
}
