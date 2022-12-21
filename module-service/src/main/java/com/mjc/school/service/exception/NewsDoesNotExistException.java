package com.mjc.school.service.exception;

public class NewsDoesNotExistException extends RuntimeException {
    public NewsDoesNotExistException(Long id) {
        System.out.println("ERROR_CODE: 000001 ERROR_MESSAGE: News with id " + id + " does not exist.");
    }
}