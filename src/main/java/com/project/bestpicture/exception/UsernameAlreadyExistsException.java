package com.project.bestpicture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameAlreadyExistsException extends ResponseStatusException {

    private System.Logger logger = System.getLogger(UsernameAlreadyExistsException.class.getName());

    public UsernameAlreadyExistsException(){
        super(HttpStatus.BAD_REQUEST, "Account with this username already exists");
        logger.log(System.Logger.Level.ERROR, "Account with this username already exists");
    }
}
