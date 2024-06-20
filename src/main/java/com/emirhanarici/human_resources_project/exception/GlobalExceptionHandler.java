package com.emirhanarici.human_resources_project.exception;

import com.emirhanarici.human_resources_project.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(JobSeekerNotFoundException.class)
    public ResponseEntity<Object> handleJobSeekerNotFoundException(JobSeekerNotFoundException ex) {
        log.error("JobSeekerNotFoundException: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(JobSeekerNotFoundException.STATUS.value())
                .status(JobSeekerNotFoundException.STATUS)
                .build();

        return ResponseEntity.status(JobSeekerNotFoundException.STATUS).body(errorResponse);
    }

}
