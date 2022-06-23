package com.project.bestpicture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractException extends ResponseStatusException {

    private System.Logger logger = System.getLogger(AbstractException.class.getName());

    public AbstractException(HttpStatus status, String message) {
        super(status, message);
        logger.log(System.Logger.Level.ERROR, message);
    }
}
