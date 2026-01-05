package com.yourorg.telemedicine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> error = new HashMap<>();
        Map<String, String> validationErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing
                ));
        
        error.put("error", "Validation Error");
        error.put("message", "Invalid input data");
        error.put("validationErrors", validationErrors);
        error.put("status", HttpStatus.BAD_REQUEST.value());
        System.err.println("GlobalExceptionHandler: MethodArgumentNotValidException - " + validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Validation Error");
        error.put("message", e.getMessage());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        System.err.println("GlobalExceptionHandler: IllegalArgumentException - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, Object> error = new HashMap<>();
        String message = "Database constraint violation";
        if (e.getMessage() != null && e.getMessage().contains("email")) {
            message = "Doctor with this email already exists";
        } else if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
            message = "Duplicate entry: " + e.getMessage();
        }
        error.put("error", "Database Error");
        error.put("message", message);
        error.put("status", HttpStatus.CONFLICT.value());
        System.err.println("GlobalExceptionHandler: DataIntegrityViolationException - " + e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        // Don't catch MethodArgumentNotValidException as RuntimeException
        // It should be caught by the specific handler above
        if (e.getCause() instanceof org.springframework.web.bind.MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException((org.springframework.web.bind.MethodArgumentNotValidException) e.getCause());
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Server Error");
        error.put("message", e.getMessage() != null ? e.getMessage() : "An unexpected error occurred");
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        System.err.println("GlobalExceptionHandler: RuntimeException - " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception e) {
        // Check if this is a wrapped validation exception
        if (e.getCause() instanceof org.springframework.web.bind.MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException((org.springframework.web.bind.MethodArgumentNotValidException) e.getCause());
        }
        
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", e.getMessage() != null ? e.getMessage() : "An unexpected error occurred");
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        System.err.println("GlobalExceptionHandler: Exception - " + e.getClass().getName() + " - " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

