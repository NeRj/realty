package com.nerj.oop.realty.exception;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(){
        super("Отрицательное значение недопустимо в данном контексте!");
    }
    public NegativeNumberException(String message){
        super(message);
    }
}
