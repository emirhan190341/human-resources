package com.emirhanarici.human_resources_project.exception;

import org.springframework.http.HttpStatus;

public class JobSeekerNotFoundException extends RuntimeException{

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public JobSeekerNotFoundException(String message) {
        super(message);
    }
}
