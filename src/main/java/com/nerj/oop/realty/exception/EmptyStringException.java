package com.nerj.oop.realty.exception;

public class EmptyStringException extends Exception {
    public EmptyStringException(){
        super("Пустая строка! Ожидалось какое-либо значение.");
    }
    public EmptyStringException(String message){
        super(message);
    }
}
