package com.example.clothesshop.exeptions;

public class IdIsInvalidException extends RuntimeException{
    public IdIsInvalidException(String message){
        super(message);
    }
}
