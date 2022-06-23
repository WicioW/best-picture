package com.project.bestpicture.exception;


import org.springframework.http.HttpStatus;

public class IncorrectRatingException extends AbstractException {

    public IncorrectRatingException() {
        super(HttpStatus.BAD_REQUEST, "Incorrect rating. Should be between 0 and 10.");
    }
}
