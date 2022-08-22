package com.group.foodorderapplicationback.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(exception.getMessage(), exception, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        log.error(exception.getMessage());

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationConstraint(ConstraintViolationException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(exception.getMessage(), null, HttpStatus.BAD_REQUEST, LocalDateTime.now());

        log.error(exception.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleSqlIntegrityConstraint(SQLIntegrityConstraintViolationException exception) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(exception.getMessage(), null, HttpStatus.CONFLICT, LocalDateTime.now());

        log.error(exception.getMessage());
        return new ResponseEntity<>(apiException, conflict);
    }
}
