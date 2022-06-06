package com.proj.flight.exception;

public class NoSuchFlightException extends Exception {
    public NoSuchFlightException() {
        super("Flight not found");
    }

    public NoSuchFlightException(String message) {
        super(message);
    }
}
