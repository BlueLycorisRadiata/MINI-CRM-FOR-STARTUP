package com.example.crm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> notFound(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body("NOT_FOUND", e.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> badRequest(IllegalArgumentException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body("BAD_REQUEST", e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validation(MethodArgumentNotValidException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body("VALIDATION_ERROR", "Invalid request"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> generic(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body("INTERNAL_ERROR", "Unexpected error"));
  }

  private Map<String, Object> body(String code, String message) {
    return Map.of(
        "timestamp", Instant.now().toString(),
        "code", code,
        "message", message
    );
  }
}
