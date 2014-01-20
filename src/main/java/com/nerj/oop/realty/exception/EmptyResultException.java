package com.nerj.oop.realty.exception;

/**
 * Created by vlad on 21.01.14.
 */
public class EmptyResultException extends Exception{
    public EmptyResultException(){
        super("В БД отсутствуют данные по вашему запросу!");
    }
    public EmptyResultException(String message){
        super(message);
    }
}
