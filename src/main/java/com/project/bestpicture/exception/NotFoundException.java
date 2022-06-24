package com.project.bestpicture.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(Class<?> klass, Long id) {
        this("Entity not found. " + klass.getSimpleName() + "[id=" + id + "]");
    }

    public NotFoundException(Class<?> klass, String name) {
        this("Entity not found. " + klass.getSimpleName() + "[name=" + name + "]");
    }
}
