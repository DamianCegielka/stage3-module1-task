package com.mjc.school.controller;

import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.ServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerImpl implements Controller {

    private static final String MENU_TEXT =
            """
                    Enter the number of operation:
                    1 - Get all news.
                    2 - Get news by id.
                    3 - Create news.
                    4 - Update news.
                    5 - Remove news by id.
                    0 - Exit.
                    """;
    private static final String GET_NEWS_ID = "Operation: Get news by id.";
    private static final String ENTER_NEWS_ID = "Enter news id";
    private static final String ENTER_TITLE = "Enter news title:";
    private static final String ENTER_CONTENT = "Enter news content:";
    private static final String ENTER_AUTHOR_ID = "Enter author id";
    private static final String CREATE_NEWS = "Create news.";
    private static final String UPDATE_NEWS = "Operation: Update news.";


    private int chosenNumber = -1;

    @Override
    public void mainController() {

        Service service = new ServiceImpl();
        service.loadAllData();
        try {
            while (chosenNumber != 0) {
                System.out.println(MENU_TEXT);
                chosenNumber = takeNumberFromKeyboard();

                switch (chosenNumber) {
                    case 1 -> service.readAllNews();
                    case 2 -> service.readByIdNews(this.takeIdForNews());
                    case 3 -> {
                        System.out.println(CREATE_NEWS);
                        service.createNews(this.askQuestionsToGetDtoRequest());
                    }
                    case 4 -> {
                        System.out.println(UPDATE_NEWS);
                        service.updateNews(this.askQuestionsTOGetDtoRequestWithIndex());
                    }
                    case 5 -> service.deleteNews();
                    case 0 -> System.out.println("By by!");
                    default -> System.out.println("Error!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int takeNumberFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(bufferedreader.readLine());
    }

    @Override
    public String takeStringFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedreader.readLine();
    }

    @Override
    public Long takeIdForNews() throws IOException {
        System.out.println(GET_NEWS_ID);
        System.out.println(ENTER_NEWS_ID);
        return Long.valueOf(takeNumberFromKeyboard());
    }

    @Override
    public NewsDtoRequest askQuestionsToGetDtoRequest() {
        NewsDtoRequest newsDTOCreation = new NewsDtoRequest();
        try {
            System.out.println(ENTER_TITLE);
            newsDTOCreation.setTitle(takeStringFromKeyboard());
            System.out.println(ENTER_CONTENT);
            newsDTOCreation.setContent(takeStringFromKeyboard());
            System.out.println(ENTER_AUTHOR_ID);
            newsDTOCreation.setAuthorId(Long.valueOf(takeNumberFromKeyboard()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDTOCreation;
    }

    @Override
    public NewsDtoRequestWithIndex askQuestionsTOGetDtoRequestWithIndex() throws IOException {
        System.out.println(ENTER_NEWS_ID);
        Long index = Long.valueOf(takeNumberFromKeyboard());
        NewsDtoRequest newsModelRequest = askQuestionsToGetDtoRequest();
        NewsDtoRequestWithIndex newsDtoRequestWithIndex = new NewsDtoRequestWithIndex();
        newsDtoRequestWithIndex.setIndex(index);
        newsDtoRequestWithIndex.setTitle(newsModelRequest.getTitle());
        newsDtoRequestWithIndex.setContent(newsModelRequest.getContent());
        newsDtoRequestWithIndex.setAuthorId(newsModelRequest.getAuthorId());
        return newsDtoRequestWithIndex;
    }


}
