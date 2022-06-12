package com.proj.demo.exception;

public class NoSuchTagException extends Exception {

    public NoSuchTagException() {
        super("Tag not found");
    }

    public NoSuchTagException(String message) {
        super(message);
    }
}
