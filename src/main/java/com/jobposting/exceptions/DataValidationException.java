package com.jobposting.exceptions;

public class DataValidationException extends RuntimeException{
    public DataValidationException(){
        super("Data validation failed");
    }
    public DataValidationException(String message){
        super(message);
    }
}
