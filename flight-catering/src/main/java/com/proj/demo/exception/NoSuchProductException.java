package com.proj.demo.exception;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException() {
        super("Product not found");
    }

    public NoSuchProductException(String message) {
        super(message);
    }
}
