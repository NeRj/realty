package com.nerj.oop.realty.exception;

/**
 * Created by vlad on 24.01.14.
 */
public class NotExistsException extends Exception {
    public NotExistsException(){
        super("Указан несуществующий ID!");
    }
    public NotExistsException(String message){
        super(message);
    }
}
