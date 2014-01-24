package com.nerj.oop.realty.exception;

public class IncorrectDateFormatException extends Exception {
    public IncorrectDateFormatException(){
        super("Некорректный формат даты!");
    }
    public IncorrectDateFormatException(String message){
        super(message);
    }
}
