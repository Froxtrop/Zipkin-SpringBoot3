package com.example.service1.exceptions;

import com.example.service1.responses.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse("BAD_REQUEST",
                        ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
