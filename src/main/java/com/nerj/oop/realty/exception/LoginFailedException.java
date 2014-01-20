package com.nerj.oop.realty.exception;

/**
 * Created by LipenVK on 13.01.14.
 */
public class LoginFailedException extends Exception {
    public LoginFailedException(){
        super("Ошибка авторизации!");
    }
    public LoginFailedException(String message){
        super(message);
    }
}
