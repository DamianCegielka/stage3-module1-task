package com.mjc.school.service.exception;

public class LengthIsNotBetween5and255Exception extends RuntimeException {
    public LengthIsNotBetween5and255Exception(String message) {
        super(message);
    }
}
