package com.mjc.school.service;

import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.LengthIsNotBetween5and255Exception;
import com.mjc.school.service.exception.LengthIsNotBetween5and30Exception;

public class Validator {


    public boolean lengthBetween5And255Symbols(String text) {
        try {
            if ((text.length() >= 5) && (text.length() <= 255)) return true;
            else throw  new LengthIsNotBetween5and255Exception(String.format(ErrorCodes.NEWS_CONTENT_LENGTH_IS_NOT_VALID.getMessage(),text));
        } catch (LengthIsNotBetween5and255Exception ex) {
            System.out.println(ex.getMessage());;
        }
        return false;
    }


    public boolean lengthBetween5And30Symbols(String text) {
        try {
            if ((text.length() >= 5) && (text.length() <= 30)) return true;
            else throw new LengthIsNotBetween5and30Exception(
                    String.format(ErrorCodes.NEWS_TITLE_LENGTH_IS_NOT_VALID.getMessage()
                            ,text));
        } catch (LengthIsNotBetween5and30Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
