package org.aviation.projects.flightcatering.exception;

public class NoSuchOrderException extends Exception {
    public NoSuchOrderException() {
        super("Order not found");
    }

    public NoSuchOrderException(String message) {
        super(message);
    }
}
