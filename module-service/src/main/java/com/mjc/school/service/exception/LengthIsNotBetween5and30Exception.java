package com.mjc.school.service.exception;

public class LengthIsNotBetween5and30Exception extends RuntimeException{
    public LengthIsNotBetween5and30Exception(String text){
        System.out.println("ERROR_CODE: 000012 ERROR_MESSAGE: News title can not be less than 5 and more than 30 symbols. News title is "+text);
    }
}
