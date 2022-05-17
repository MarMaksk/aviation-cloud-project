package com.proj.flight.exception;

public class NoSuchAirplaneException extends RuntimeException {
    public NoSuchAirplaneException() {
        super("Airplane not found");
    }

    public NoSuchAirplaneException(String message) {
        super(message);
    }
}
