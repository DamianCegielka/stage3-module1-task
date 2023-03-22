package com.mjc.school.service.exception;

public class LengthIsNotBetween5and30Exception extends RuntimeException {
    public LengthIsNotBetween5and30Exception(String message) {
        super(message);
    }
}
