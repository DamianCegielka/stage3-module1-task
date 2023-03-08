package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.ServiceDto;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.AuthorIdDoesNotExistException;
import com.mjc.school.service.impl.ServiceDtoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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

    private static final String GET_ALL_NEWS = "Operation: Get all news.";
    private static final String GET_NEWS_ID = "Operation: Get news by id.";
    private static final String ENTER_NEWS_ID = "Enter news id";
    private static final String ENTER_TITLE = "Enter news title:";
    private static final String ENTER_CONTENT = "Enter news content:";
    private static final String ENTER_AUTHOR_ID = "Enter author id";
    private static final String CREATE_NEWS = "Create news.";
    private static final String UPDATE_NEWS = "Operation: Update news.";
    private static final String REMOVE_NEWS = "Operation: Remove news by id.";

    private final ServiceDto controllerServiceDto = new ServiceDtoImpl();

    private int chosenNumber = -1;


    @Override
    public void mainController() throws IOException {

        controllerServiceDto.loadAllData();
        try {
            while (chosenNumber != 0) {
                System.out.println(MENU_TEXT);
                chosenNumber = takeNumberFromKeyboard();

                switch (chosenNumber) {
                    case 1 -> {
                        System.out.println(GET_ALL_NEWS);
                        this.readAllNews();
                    }
                    case 2 -> {
                        System.out.println(GET_NEWS_ID);
                        this.readByIdNews(this.takeIdNews());
                    }
                    case 3 -> {
                        System.out.println(CREATE_NEWS);
                        this.createNews(this.askQuestionsToGetDtoRequest());
                    }
                    case 4 -> {
                        System.out.println(UPDATE_NEWS);
                        this.updateNews(this.askQuestionsTOGetDtoRequestWithIndex());
                    }
                    case 5 -> {
                        System.out.println(REMOVE_NEWS);
                        this.deleteNews(this.takeIdNews());
                    }
                    case 0 -> System.out.println("By by!");
                    default -> System.out.println("Error!");
                }
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
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


    public Long takeIdNews() throws IOException {
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

    @Override
    public List<NewsDtoResponse> readAllNews() throws IOException {
        return controllerServiceDto.readAllNews();
    }

    @Override
    public NewsDtoResponse readByIdNews(Long index) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            newsDtoResponse = controllerServiceDto.readByIdNews(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            newsDtoResponse = controllerServiceDto.createNews(newsDtoRequest);
        } catch (AuthorIdDoesNotExistException e) {
            System.out.println("2");
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse updateNews(NewsDtoRequestWithIndex newsDtoRequestWithIndex) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            newsDtoResponse = controllerServiceDto.updateNews(newsDtoRequestWithIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public Boolean deleteNews(Long index) {
        Boolean result = false;
        try {
            result = controllerServiceDto.deleteNews(index);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
