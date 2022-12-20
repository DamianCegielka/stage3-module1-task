package com.mjc.school.service;

import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.repository.entity.News;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.LengthIsNotBetween5and255Exception;

import java.io.IOException;
import java.util.List;

public interface Service {

    void loadAllData();

    List<News> readAllNews() throws IOException;

    void readByIdNews();

    NewsDtoResponse createNews();

    NewsDtoResponse updateNews();

    void deleteNews();

    NewsModelRequest askQuestionsToGetDtoRequest();

    String takeStringFromKeyboard() throws IOException;

    Long takeNumberFromKeyboard();

    boolean lengthBetween5And255Symbols(String text) throws LengthIsNotBetween5and255Exception;

    boolean lengthBetween5And30Symbols(String text);

}
