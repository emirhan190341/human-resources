package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class JobNotFoundException extends RuntimeException{

    public  static final HttpStatus STATUS  = HttpStatus.NOT_FOUND;

    public JobNotFoundException(String message) {
        super(message);
    }
}
