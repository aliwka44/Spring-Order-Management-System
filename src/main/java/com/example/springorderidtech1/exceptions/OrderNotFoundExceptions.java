package com.example.springorderidtech1.exceptions;

public class OrderNotFoundExceptions extends RuntimeException {
    public OrderNotFoundExceptions(String message) {
        super(message);
    }
}
