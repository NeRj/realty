package com.nerj.oop.realty.exception;

/**
 * Created by vlad on 20.01.14.
 */
public class IncorrectChoiceException extends Exception {
    public IncorrectChoiceException(){
        super("Некорректное значение! Вы ввели неверный символ.");
    }
    public IncorrectChoiceException(String message){
        super(message);
    }
}
