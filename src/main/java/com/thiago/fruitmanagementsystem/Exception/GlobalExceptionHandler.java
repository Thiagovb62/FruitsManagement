package com.thiago.fruitmanagementsystem.Exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((message1, message2) -> message1 + "; " + message2)
                .orElse("Validation failed");
        ErrorDetail errorDetail = new ErrorDetail("validation-error", "Validation Error", HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetail> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .reduce((message1, message2) -> message1 + "; " + message2)
                .orElse("Constraint violation error");
        ErrorDetail errorDetail = new ErrorDetail("constraint-violation-error", "Constraint Violation Error", HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorDetail errorDetail = new ErrorDetail("entity-not-found-error", "Entity Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(org.apache.tomcat.websocket.AuthenticationException.class)
    public ResponseEntity<ErrorDetail> handleAuthenticationException(AuthenticationException ex) {
        ErrorDetail errorDetail = new ErrorDetail("authentication-error", "Authentication Error", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetail> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorDetail errorDetail = new ErrorDetail("illegal-argument-error", "Illegal Argument Error", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorDetail> handleJWTVerificationException(JWTVerificationException ex) {
        ErrorDetail errorDetail = new ErrorDetail("jwt-verification-error", "JWT Verification Error", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception ex) {
        ErrorDetail errorDetail = new ErrorDetail("internal-server-error", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetail> handleNullPointerException(NullPointerException ex) {
        ErrorDetail errorDetail = new ErrorDetail("null-pointer-error", "Null Pointer Error", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}