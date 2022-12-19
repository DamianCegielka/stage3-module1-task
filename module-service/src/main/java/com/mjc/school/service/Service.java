package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.service.exception.LengthIsNotBetween5and255Exception;

import java.io.IOException;

public interface Service {

    void loadAllData();

    void readAllNews() throws IOException;

    void readNewsById();

    void createNews();

    void updateNews();

    void removeNews();

    NewsModelRequest askQuestionsToGetDtoRequest();

    String takeStringFromKeyboard() throws IOException;

    Long takeNumberFromKeyboard();

    boolean lengthBetween5And255Symbols(String text) throws LengthIsNotBetween5and255Exception;

    boolean lengthBetween5And30Symbols(String text);

}
