package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }
}
