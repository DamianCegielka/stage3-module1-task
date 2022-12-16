package com.mjc.school.service;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.RepositoryImpl;
import com.mjc.school.repository.dto.NewsDtoRequest;
import com.mjc.school.service.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceImpl implements Service {

    private static final String CREATE_NEWS = "Create news.";
    private static final String ENTER_TITLE = "Enter news title:";
    private static final String ENTER_CONTENT = "Enter news content:";
    private static final String ENTER_AUTHOR_ID = "Enter author id";
    private static final String GET_ALL_NEWS = "Operation: Get all news.";
    private static final String GET_NEWS_ID = "Operation: Get news by id.";
    private static final String ENTER_NEWS_ID = "Enter news id";
    private static final String UPDATE_NEWS = "Operation: Update news.";
    private static final String REMOVE_NEWS = "Operation: Remove news by id.";
    private final Repository repository = new RepositoryImpl();

    public void loadAllData() {
        repository.loadNewsFromFiles();
        repository.loadAuthorsFromFiles();
    }

    @Override
    public void readAllNews() throws IOException {
        System.out.println(GET_ALL_NEWS);
        repository.getAllNews();
    }

    @Override
    public void readNewsById() throws IOException {
        try {
            System.out.println(GET_NEWS_ID);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            if (repository.isNewsOnList(index)) {
                repository.getOneNews(index);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createNews() {
        try {
            System.out.println(CREATE_NEWS);
            NewsDtoRequest newsDtoRequest = askQuestionsToGetDtoRequest();
            lengthBetween5And30Symbols(newsDtoRequest.getTitle());
            lengthBetween5And255Symbols(newsDtoRequest.getContent());
            if (!repository.isAuthorOnList(newsDtoRequest.getAuthorId())){throw new AuthorIdDoesNotExistException(newsDtoRequest.getAuthorId());}
            repository.addNews(newsDtoRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNews() throws IOException {
        try {
            System.out.println(UPDATE_NEWS);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            NewsDtoRequest newsDtoRequest = askQuestionsToGetDtoRequest();
            if (repository.isNewsOnList(index)) {
                if (repository.isAuthorOnList(newsDtoRequest.getAuthorId())) {
                    if ((lengthBetween5And30Symbols(newsDtoRequest.getTitle())) &&
                            (lengthBetween5And255Symbols(newsDtoRequest.getContent()))){
                            repository.updateNews(index, newsDtoRequest);
                    }
                } else {
                    throw new AuthorIdDoesNotExistException(newsDtoRequest.getAuthorId());
                }
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeNews() throws IOException {
        try {
            System.out.println(REMOVE_NEWS);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            if (repository.isNewsOnList(index)) {
                repository.deleteNews(index);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            newsDTOCreation.setAuthorId(takeNumberFromKeyboard());
        } catch (Exception e) {
            this.createNews();
        }
        return newsDTOCreation;
    }

    @Override
    public String takeStringFromKeyboard() throws IOException {
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedreader.readLine();
    }

    @Override
    public Long takeNumberFromKeyboard() {
        try {
            return Long.parseLong(takeStringFromKeyboard());
        } catch (Exception e) {
            throw new AuthorIdShouldBeNummberException();
        }
    }

    @Override
    public boolean lengthBetween5And255Symbols(String text) {
        try {
            if ((text.length() >= 5) && (text.length() <= 255)) return true;
            else throw new IOException();
        } catch (Exception e) {
            throw new LengthIsNotBetween5and255Exception(text);
        }
    }

    @Override
    public boolean lengthBetween5And30Symbols(String text) {
        try {
            if ((text.length() >= 5) && (text.length() <= 30)) return true;
            else throw new IOException();
        } catch (Exception e) {
            throw new LengthIsNotBetween5and30Exception(text);
        }
    }


}
