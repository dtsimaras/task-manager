package com.tsimo.taskservice;

import com.tsimo.taskservice.task.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<Void> handleNotFound(TaskNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Invalid value: " + e.getMostSpecificCause().getMessage());
    }
}