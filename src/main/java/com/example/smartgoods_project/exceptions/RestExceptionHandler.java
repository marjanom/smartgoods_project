package com.example.smartgoods_project.exceptions;

import com.example.smartgoods_project.rest.model.ResponseMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class catches exceptions from REST controllers and handles them as specified in the individual methods.
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleDuplicateUsername(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseMessageDto(e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleMissingUuid(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessageDto(e.getMessage()));
    }
    @ExceptionHandler(RequirementNotExistsException.class)
    public ResponseEntity<?> handleMissingRequirement(RequirementNotExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessageDto(e.getMessage()));
    }

    @ExceptionHandler(ProjectAlreadyExistsException.class)
    public ResponseEntity<?> handleMissingRequirement(ProjectAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessageDto(e.getMessage()));
    }

    @ExceptionHandler(ProjectNotExistsException.class)
    public ResponseEntity<?> handleMissingRequirement(ProjectNotExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessageDto(e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleUserCredentialsInvalid(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseMessageDto(e.getMessage()));
    }





}
