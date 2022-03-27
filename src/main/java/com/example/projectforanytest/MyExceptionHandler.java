package com.example.projectforanytest;

import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    private ResponseEntity handleOptimistic(ObjectOptimisticLockingFailureException e) {
        return ResponseEntity.badRequest().body("예외 발생!");
    }
}
