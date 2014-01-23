package com.nerj.oop.realty.exception;

public class EmptyResultException extends Exception{
    public EmptyResultException(){
        super("В БД отсутствуют данные по вашему запросу!");
    }
    public EmptyResultException(String message){
        super(message);
    }
}
