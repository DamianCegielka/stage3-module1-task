package com.mjc.school.service.exception;

public class AuthorIdShouldBeNumberException extends RuntimeException {
    public AuthorIdShouldBeNumberException() {
        System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
    }
}
