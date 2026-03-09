package com.example.springorderidtech1.controller;

import com.example.springorderidtech1.exceptions.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.springorderidtech1.constants.ExceptionsCodes.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundExceptions.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleExceptions(OrderNotFoundExceptions exceptions) {
        return new ExceptionResponse(ORDER_NOT_FOUND_CODE, exceptions.getMessage());
    }

    @ExceptionHandler(ProductNotFoundExceptions.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleExceptions(ProductNotFoundExceptions exceptions) {
        return new ExceptionResponse(PRODUCT_NOT_FOUND_CODE, exceptions.getMessage());
    }

    @ExceptionHandler(ProductNotAvailabilityExceptions.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleExceptions(ProductNotAvailabilityExceptions exceptions) {
        return new ExceptionResponse(ORDER_NOT_AVAILABILITY_CODE, exceptions.getMessage());
    }

    @ExceptionHandler(CardNotFoundExceptions.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleExceptions(CardNotFoundExceptions exceptions) {
        return new ExceptionResponse(CARD_NOT_FOUND_CODE, exceptions.getMessage());
    }

    @ExceptionHandler(CardNotBalanceExceptions.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleExceptions(CardNotBalanceExceptions exceptions) {
        return new ExceptionResponse(CARD_NOT_BALANCE_CODE, exceptions.getMessage());
    }

    @ExceptionHandler(DataMismatchException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionResponse handleExceptions(DataMismatchException exceptions) {
        return new ExceptionResponse(PRODUCT_MISMATCH_CODE, exceptions.getMessage());
    }
}
