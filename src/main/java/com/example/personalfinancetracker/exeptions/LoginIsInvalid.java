package com.example.personalfinancetracker.exeptions;

public class LoginIsInvalid extends RuntimeException{
    public LoginIsInvalid(String message){
        super(message);
    }
}
