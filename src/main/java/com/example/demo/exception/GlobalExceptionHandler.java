package com.example.demo.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> manageException(Exception e) {
        logger.error("error occurs: " + e.getMessage());
        //System.out.println(e);
        return new ResponseEntity<>("some error occurs in server", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
