package org.aviation.projects.flightorder.exception;

public class NoSuchSeatException extends Exception {
    public NoSuchSeatException() {
        super("Seat not found");
    }

    public NoSuchSeatException(String message) {
        super(message);
    }
}
