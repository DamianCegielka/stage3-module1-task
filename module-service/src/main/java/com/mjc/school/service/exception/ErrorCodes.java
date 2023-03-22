package com.mjc.school.service.exception;

public enum ErrorCodes {

    NEWS_ID_DOES_NOT_EXIST("000001", "News with id % does not exist."),
    AUTHOR_ID_DOES_NOT_EXIST("000002", "Author Id does not exist. Author Id is: %s"),
    AUTHOR_ID_SHOULD_BE_NUMBER("000013", "Author Id should be number"),
    NEWS_TITLE_LENGTH_IS_NOT_VALID("000012" ,
                            "News title can not be less than 5 and more than 30 symbols. News title is %s"),
    NEWS_CONTENT_LENGTH_IS_NOT_VALID("000012",
                        "News content can not be less than 5 and more than 255 symbols. News content is %s");


    private final String errorMessage;

    ErrorCodes(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getMessage() {
        return errorMessage;
    }
}
