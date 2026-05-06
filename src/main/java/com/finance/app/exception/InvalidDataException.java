package com.finance.app.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }
}
