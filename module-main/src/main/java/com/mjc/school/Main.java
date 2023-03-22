package com.mjc.school;


import com.mjc.school.controller.Controller;
import com.mjc.school.controller.impl.ControllerImpl;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.mjc.school.Constans.*;

public class Main {

    private static int chosenNumber = -1;

    public static void main(String[] args) throws IOException {
        Controller controller = new ControllerImpl();

        try {
            controller.loadData();
            while (chosenNumber != 0) {
                System.out.println(MENU_TEXT);
                chosenNumber = takeNumberFromKeyboard();

                switch (chosenNumber) {
                    case 1 -> {
                        System.out.println(GET_ALL_NEWS);
                        controller.readAllNews();
                    }
                    case 2 -> {
                        System.out.println(GET_NEWS_ID);
                        controller.readByIdNews(takeIdNews());
                    }
                    case 3 -> {
                        System.out.println(CREATE_NEWS);
                        controller.createNews(askQuestionsToGetDtoRequest());
                    }
                    case 4 -> {
                        System.out.println(UPDATE_NEWS);
                        controller.updateNews(askQuestionsTOGetDtoRequestWithIndex());
                    }
                    case 5 -> {
                        System.out.println(REMOVE_NEWS);
                        controller.deleteNews(takeIdNews());
                    }
                    case 0 -> System.out.println(BY_BY);
                    default -> System.out.println(ERROR);
                }
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Long takeIdNews() throws IOException {
        System.out.println(ENTER_NEWS_ID);
        return Long.valueOf(takeNumberFromKeyboard());
    }

    public static int takeNumberFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(bufferedreader.readLine());
    }


    public static String takeStringFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedreader.readLine();
    }

    public static  NewsDtoRequest askQuestionsToGetDtoRequest() {
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

    public static NewsDtoRequestWithIndex askQuestionsTOGetDtoRequestWithIndex() throws IOException {
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