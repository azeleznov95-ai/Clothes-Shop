package com.example.clothesshop.exeptions;

public class EmptyUserException extends RuntimeException{
    public EmptyUserException(String message){
        super(message);
    }

}
