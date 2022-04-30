package com.proj.flight.exception;

public class NoSuchExaminationException extends RuntimeException{
    public NoSuchExaminationException() {
        super("Examination not found");
    }

    public NoSuchExaminationException(String message) {
        super(message);
    }
}
