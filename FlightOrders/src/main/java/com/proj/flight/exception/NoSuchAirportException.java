package com.proj.flight.exception;

public class NoSuchAirportException extends RuntimeException {
    public NoSuchAirportException() {
        super("Airport not found");
    }

    public NoSuchAirportException(String message) {
        super(message);
    }
}
