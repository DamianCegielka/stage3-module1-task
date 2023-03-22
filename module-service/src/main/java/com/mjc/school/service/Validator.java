package com.mjc.school.service;

import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.StringLengthIsNotCorrectException;

public class Validator {


    public boolean validateLengthNewsContent(String text) {
        try {
            if ((text.length() >= StringLength.IS_5) && (text.length() <= StringLength.IS_255)) return true;
            else throw  new StringLengthIsNotCorrectException(
                    String.format(ErrorCodes.NEWS_CONTENT_LENGTH_IS_NOT_VALID.getMessage(),text));
        } catch (StringLengthIsNotCorrectException ex) {
            System.out.println(ex.getMessage());;
        }
        return false;
    }


    public boolean validateLengthNewsTitle(String text) {
        try {
            if ((text.length() >= StringLength.IS_5) && (text.length() <= StringLength.IS_30)) return true;
            else throw new StringLengthIsNotCorrectException(
                    String.format(ErrorCodes.NEWS_TITLE_LENGTH_IS_NOT_VALID.getMessage(),text));
        } catch (StringLengthIsNotCorrectException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
