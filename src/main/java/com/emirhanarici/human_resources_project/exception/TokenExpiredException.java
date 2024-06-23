package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends RuntimeException{

    public static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public TokenExpiredException(String message) {
        super(message);
    }
}
