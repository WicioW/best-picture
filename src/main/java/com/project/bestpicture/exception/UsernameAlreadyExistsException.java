package com.project.bestpicture.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends AbstractException {

    public UsernameAlreadyExistsException(){
        super(HttpStatus.BAD_REQUEST, "Account with this username already exists");
    }
}
