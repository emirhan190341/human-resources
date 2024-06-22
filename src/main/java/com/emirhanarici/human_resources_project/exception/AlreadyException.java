package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class AlreadyException extends RuntimeException{

    public static final HttpStatus STATUS = HttpStatus.CONFLICT;

    public AlreadyException(String message) {
        super(message);
    }
}
