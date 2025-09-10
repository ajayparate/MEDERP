package com.mederp.customexception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDBErrors(DataIntegrityViolationException ex) {
        return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body("Database constraint violation: " + ex.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        log.error("Unhandled exception:", ex);
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Internal server error—please contact support.");
    }

}
