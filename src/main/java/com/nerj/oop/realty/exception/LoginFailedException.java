package com.nerj.oop.realty.exception;

public class LoginFailedException extends Exception {
    public LoginFailedException(){
        super("Ошибка авторизации!");
    }
    public LoginFailedException(String message){
        super(message);
    }
}
