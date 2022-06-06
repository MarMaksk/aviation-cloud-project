package com.proj.flight.exception;

public class NoSuchAirplaneException extends Exception {
    public NoSuchAirplaneException() {
        super("Airplane not found");
    }

    public NoSuchAirplaneException(String message) {
        super(message);
    }
}
