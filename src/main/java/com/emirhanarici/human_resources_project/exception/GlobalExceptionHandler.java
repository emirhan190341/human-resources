package com.emirhanarici.human_resources_project.exception;

import com.emirhanarici.human_resources_project.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<Object> handleJobNotFoundException(JobNotFoundException ex) {
        log.error("JobNotFoundException: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(JobSeekerNotFoundException.STATUS.value())
                .status(JobSeekerNotFoundException.STATUS)
                .build();

        return ResponseEntity.status(JobNotFoundException.STATUS).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
