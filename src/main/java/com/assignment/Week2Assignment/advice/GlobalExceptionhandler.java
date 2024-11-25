package com.assignment.Week2Assignment.advice;

import com.assignment.Week2Assignment.exceptions.NoResourceFoundException;
import com.assignment.Week2Assignment.responses.ApiErrorResponse;
import com.assignment.Week2Assignment.responses.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleNoResourceFoundException(NoResourceFoundException exception){
        ApiErrorResponse errorResponse=ApiErrorResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .msg(exception.getMessage())
                .build();
        return buildErrorResponseEntity(errorResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalApiResponse<?>> handleValidationException(MethodArgumentNotValidException exception){
     List<String> subErrors=exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());

     ApiErrorResponse errorResponse=ApiErrorResponse.builder()
             .httpStatus(HttpStatus.BAD_REQUEST)
             .msg("Validation Failed.")
             .subErrors(subErrors)
             .build();
     return buildErrorResponseEntity(errorResponse);
    }


    private ResponseEntity<GlobalApiResponse<?>> buildErrorResponseEntity(ApiErrorResponse errorResponse){
        return new ResponseEntity<>(new GlobalApiResponse<>(errorResponse),errorResponse.getHttpStatus());
    }
}
