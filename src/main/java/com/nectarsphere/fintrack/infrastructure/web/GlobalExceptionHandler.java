package com.nectarsphere.fintrack.infrastructure.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.nectarsphere.fintrack.domain.exception.InvalidUserDataException;
import com.nectarsphere.fintrack.domain.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgument(
      IllegalArgumentException ex, WebRequest request) {

    logger.warn("Invalid argument: {}", ex.getMessage());

    ErrorResponse error = new ErrorResponse(
        "INVALID_ARGUMENT",
        ex.getMessage(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.badRequest().body(error);
      }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(
      RuntimeException ex, WebRequest request) {

    logger.error("Runtime exception occurred", ex);

    ErrorResponse error = new ErrorResponse(
        "INTERNAL_ERROR",
        "An unexpected error occurred",
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
      }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(
      Exception ex, WebRequest request) {

    logger.error("Unexpected exception occurred", ex);

    ErrorResponse error = new ErrorResponse(
        "UNKNOWN_ERROR",
        "An unexpected error occurred",
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
      }


  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFound(
      UserNotFoundException ex, WebRequest request) {

    logger.warn("User not found: {}", ex.getMessage());

    ErrorResponse error = new ErrorResponse(
        "USER_NOT_FOUND",
        ex.getMessage(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
      }

  @ExceptionHandler(InvalidUserDataException.class)
  public ResponseEntity<ErrorResponse> handleInvalidUserData(
      InvalidUserDataException ex, WebRequest request) {

    logger.warn("Invalid user data: {}", ex.getMessage());

    ErrorResponse error = new ErrorResponse(
        "INVALID_USER_DATA",
        ex.getMessage(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.badRequest().body(error);
      }

  // Add validation error handling
  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(
      org.springframework.web.bind.MethodArgumentNotValidException ex, WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage())
        );

    logger.warn("Validation errors: {}", errors);

    ErrorResponse error = new ErrorResponse(
        "VALIDATION_ERROR",
        "Invalid request data: " + errors.toString(),
        LocalDateTime.now(),
        request.getDescription(false)
        );

    return ResponseEntity.badRequest().body(error);
      }


  public static class ErrorResponse {
    private final String code;
    private final String message;
    private final LocalDateTime timestamp;
    private final String path;

    public ErrorResponse(String code, String message, LocalDateTime timestamp, String path) {
      this.code = code;
      this.message = message;
      this.timestamp = timestamp;
      this.path = path;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getPath() { return path; }
  }
}

