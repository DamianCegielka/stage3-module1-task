package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;

import java.io.IOException;

public interface Controller {

    void mainController() ;

    int takeNumberFromKeyboard() throws IOException;
    String takeStringFromKeyboard() throws IOException;
    Long takeIdForNews() throws IOException;
    NewsDtoRequest askQuestionsToGetDtoRequest();
    NewsDtoRequestWithIndex askQuestionsTOGetDtoRequestWithIndex() throws IOException;
}
