package com.nerj.oop.realty.exception;

public class IncorrectChoiceException extends Exception {
    public IncorrectChoiceException(){
        super("Некорректное значение! Вы ввели неверный символ.");
    }
    public IncorrectChoiceException(String message){
        super(message);
    }
}
