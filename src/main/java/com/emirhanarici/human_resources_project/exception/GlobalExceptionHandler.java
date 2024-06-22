package com.emirhanarici.human_resources_project.exception;

import com.emirhanarici.human_resources_project.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.error(ex.getMessage(), ex);

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorDetails(details)
                .message("Validation Errors")
                .statusCode(status.value())
                .status(HttpStatus.valueOf(status.value()))
                .build();

        return ResponseEntity.status(status).body(errorResponse);

    }


    @ExceptionHandler(AlreadyException.class)
    public ResponseEntity<Object> handleAlreadyException(AlreadyException ex) {
        log.error("AlreadyException: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(AlreadyException.STATUS.value())
                .status(AlreadyException.STATUS)
                .build();

        return ResponseEntity.status(AlreadyException.STATUS).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        log.error("NotFoundException: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .statusCode(NotFoundException.STATUS.value())
                .status(NotFoundException.STATUS)
                .build();

        return ResponseEntity.status(NotFoundException.STATUS).body(errorResponse);
    }



}
