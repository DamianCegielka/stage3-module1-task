package com.mjc.school.service.exception;

public class AuthorIdDoesNotExistException extends RuntimeException {

    public AuthorIdDoesNotExistException(Long id) {
        System.out.println("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: " + id);
    }
}
