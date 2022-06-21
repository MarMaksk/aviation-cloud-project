package org.aviation.projects.flightorder.exception;

public class NoSuchExaminationException extends Exception {
    public NoSuchExaminationException() {
        super("Examination not found");
    }

    public NoSuchExaminationException(String message) {
        super(message);
    }
}
