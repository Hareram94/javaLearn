package com.example.minilearn.exception;

import com.example.minilearn.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //User not found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException exception){

        ApiError response = new ApiError(
                false,
                exception.getMessage(),
                new HashMap<>()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    //Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException exception){

        Map<String,String> errorse = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> {
                            errorse.put(
                                    error.getField(),
                                    error.getDefaultMessage()
                            );
                        });

        ApiError response = new ApiError(
                false,
                "Validation failed",
                errorse
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

    }

}
