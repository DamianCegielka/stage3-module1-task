package com.mjc.school.service.exception;

public class StringLengthIsNotCorrectException extends RuntimeException {
    public StringLengthIsNotCorrectException(String message) {
        super(message);
    }
}
