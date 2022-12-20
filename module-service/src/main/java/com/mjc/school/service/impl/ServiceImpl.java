package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;
import com.mjc.school.repository.impl.RepositoryImpl;
import com.mjc.school.service.Service;
import com.mjc.school.service.Validator;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ServiceImpl implements Service {

    private static final String ENTER_TITLE = "Enter news title:";
    private static final String ENTER_CONTENT = "Enter news content:";
    private static final String ENTER_AUTHOR_ID = "Enter author id";
    private static final String GET_ALL_NEWS = "Operation: Get all news.";
    private static final String ENTER_NEWS_ID = "Enter news id";
     private static final String REMOVE_NEWS = "Operation: Remove news by id.";
    private final Repository repository = new RepositoryImpl();

    private final Validator validator = new Validator();

    public void loadAllData() {
        repository.loadDataFromDataSource();
    }

    @Override
    public List<News> readAllNews() throws IOException {
        System.out.println(GET_ALL_NEWS);
        List<News> newsList = repository.readAllNews();
        return newsList;
    }

    @Override
    public NewsDtoResponse readByIdNews(Long index) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            if (repository.isNewsOnList(index)) {
                NewsModelResponse newsModelResponse = repository.readByIdNews(index);
                newsDtoResponse.map(newsModelResponse);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;

    }

    @Override
    public NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            lengthBetween5And30Symbols(newsDtoRequest.getTitle());
            lengthBetween5And255Symbols(newsDtoRequest.getContent());
            if (!repository.isAuthorOnList(newsDtoRequest.getAuthorId())) {
                throw new AuthorIdDoesNotExistException(newsDtoRequest.getAuthorId());
            }
            NewsModelResponse newsModelResponse = repository.createNews(newsDtoRequest.mapToNewsModelRequest());
            newsDtoResponse.map(newsModelResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse updateNews(NewsDtoRequestWithIndex newsDtoRequestWithIndex) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            if (repository.isNewsOnList(newsDtoRequestWithIndex.getIndex())) {
                if (repository.isAuthorOnList(newsDtoRequestWithIndex.getAuthorId())) {
                    if ((lengthBetween5And30Symbols(newsDtoRequestWithIndex.getTitle())) &&
                            (lengthBetween5And255Symbols(newsDtoRequestWithIndex.getContent()))) {
                        NewsModelResponse newsModelResponse = repository.updateNews(newsDtoRequestWithIndex.mapToNewsModelRequestWithIndex());
                        newsDtoResponse.map(newsModelResponse);
                    }
                } else {
                    throw new AuthorIdDoesNotExistException(newsDtoRequestWithIndex.getAuthorId());
                }
            } else {
                throw new NewsDoesNotExistException(newsDtoRequestWithIndex.getIndex());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public Boolean deleteNews() {
        Boolean result = false;
        try {
            System.out.println(REMOVE_NEWS);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            if (repository.isNewsOnList(index)) {
                result = repository.deleteNews(index);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public NewsModelRequest askQuestionsToGetDtoRequest() {
        NewsModelRequest newsDTOCreation = new NewsModelRequest();
        try {
            System.out.println(ENTER_TITLE);
            newsDTOCreation.setTitle(takeStringFromKeyboard());
            System.out.println(ENTER_CONTENT);
            newsDTOCreation.setContent(takeStringFromKeyboard());
            System.out.println(ENTER_AUTHOR_ID);
            newsDTOCreation.setAuthorId(takeNumberFromKeyboard());
        } catch (Exception e) {
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
