package com.nerj.oop.realty.exception;

/**
 * Created by vlad on 20.01.14.
 */
public class EmptyStringException extends Exception {
    public EmptyStringException(){
        super("Пустая строка! Ожидалось какое-либо значение.");
    }
    public EmptyStringException(String message){
        super(message);
    }
}
