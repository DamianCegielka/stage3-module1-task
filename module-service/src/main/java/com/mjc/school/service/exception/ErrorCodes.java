package com.mjc.school.service.exception;

public enum ErrorCodes {
    AUTHOR_ID_DOES_NOT_EXIST("000002", "Author Id does not exist. Author Id is: %s");
    private final String errorMessage;

    ErrorCodes(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getMessage() {
        return errorMessage;
    }
}
