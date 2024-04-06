package com.gisdev.crmshm.exception;

public class UserNotFoundException extends BadRequestException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
