package com.jobposting.exceptions;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(){
        super("Resource is not available on server");
    }
  public ResourceNotFoundException(String message){
        super(message);
    }
}
