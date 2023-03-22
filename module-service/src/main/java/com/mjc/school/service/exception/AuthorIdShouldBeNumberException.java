package com.mjc.school.service.exception;

public class AuthorIdShouldBeNumberException extends RuntimeException {
    public AuthorIdShouldBeNumberException(String message) {
        super(message);
    }
}
