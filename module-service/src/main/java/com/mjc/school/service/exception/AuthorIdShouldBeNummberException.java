package com.mjc.school.service.exception;

public class AuthorIdShouldBeNummberException extends RuntimeException{
    public AuthorIdShouldBeNummberException(){
        System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
    }
}
