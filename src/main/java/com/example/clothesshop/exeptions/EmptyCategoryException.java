package com.example.clothesshop.exeptions;

public class EmptyCategoryException extends RuntimeException{
    public EmptyCategoryException(String message){
        super(message);
    }
}
