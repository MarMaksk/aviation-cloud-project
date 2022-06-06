package com.proj.demo.exception;

public class NoSuchProductException extends Exception {

    public NoSuchProductException() {
        super("Product not found");
    }

    public NoSuchProductException(String message) {
        super(message);
    }
}
