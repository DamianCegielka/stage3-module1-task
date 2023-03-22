package com.mjc.school.service.exception;

public class NewsDoesNotExistException extends RuntimeException {
    public NewsDoesNotExistException(String message) {
        super(message);
    }
}