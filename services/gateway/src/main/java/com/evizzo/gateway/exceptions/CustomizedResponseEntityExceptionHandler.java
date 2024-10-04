package com.evizzo.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorDetails>> handleAllExceptions(Exception ex, ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), exchange.getRequest().getPath().toString());

        return Mono.just(new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ErrorDetails>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, ServerWebExchange exchange) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Validation failed. Errors: " + errors,
                exchange.getRequest().getPath().toString());

        return Mono.just(new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler(IllegalStateException.class)
    public Mono<ResponseEntity<ErrorDetails>> handleIllegalStateException(IllegalStateException ex, ServerWebExchange exchange) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), exchange.getRequest().getPath().toString());

        return Mono.just(new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT));
    }
}
