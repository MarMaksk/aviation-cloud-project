package com.proj.demo.exception;

public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException() {
        super("Order not found");
    }

    public NoSuchOrderException(String message) {
        super(message);
    }
}
