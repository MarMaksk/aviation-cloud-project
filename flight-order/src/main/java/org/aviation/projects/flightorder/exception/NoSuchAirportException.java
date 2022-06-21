package org.aviation.projects.flightorder.exception;

public class NoSuchAirportException extends Exception {
    public NoSuchAirportException() {
        super("Airport not found");
    }

    public NoSuchAirportException(String message) {
        super(message);
    }
}
