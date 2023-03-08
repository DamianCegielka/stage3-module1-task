package com.mjc.school.service.exception;

public class AuthorIdDoesNotExistException extends RuntimeException {
    public AuthorIdDoesNotExistException(String message) {
        super(message);
    }
}
