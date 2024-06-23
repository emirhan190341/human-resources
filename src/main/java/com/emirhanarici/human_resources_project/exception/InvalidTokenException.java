package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RuntimeException{

    public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public InvalidTokenException(String message) {
        super(message);
    }
}
