package com.red.userexceptions.exception;

import com.red.userexceptions.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorMessageDto buildError(String message, String details) {
        ErrorMessageDto error = new ErrorMessageDto();
        error.setMessage(message);
        error.setDetails(details);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFound(ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(buildError(ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorMessageDto> handleDuplicate(DuplicateResourceException ex, WebRequest request) {

        ErrorMessageDto error = new ErrorMessageDto();
        error.setMessage(ex.getMessage());
        error.setDetails(request.getDescription(false));
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessageDto> handleBadRequest(BadRequestException ex, WebRequest request) {

        return new ResponseEntity<>(buildError(ex.getMessage(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        String errors = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.joining(", "));

        return new ResponseEntity<>(buildError(errors, request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleGeneric(Exception ex, WebRequest request) {

        return new ResponseEntity<>(buildError("Internal server error", request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}