package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.dto.NewsModelRequestWithIndex;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;
import com.mjc.school.repository.impl.RepositoryImpl;
import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.service.Service;
import com.mjc.school.service.Validator;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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

    private final Validator validator=new Validator();

    public void loadAllData() {
        repository.loadDataFromDataSource();
    }

    @Override
    public List<News> readAllNews() throws IOException {
        System.out.println(GET_ALL_NEWS);
        List<News> newsList=repository.readAllNews();
        return newsList;
    }

    @Override
    public void readByIdNews() {
        try {
            System.out.println(GET_NEWS_ID);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            if (repository.isNewsOnList(index)) {
                repository.readByIdNews(index);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public NewsDtoResponse createNews() {
        NewsDtoResponse newsDtoResponse=new NewsDtoResponse();
        try {
            System.out.println(CREATE_NEWS);
            NewsModelRequest newsModelRequest = askQuestionsToGetDtoRequest();
            lengthBetween5And30Symbols(newsModelRequest.getTitle());
            lengthBetween5And255Symbols(newsModelRequest.getContent());
            if (!repository.isAuthorOnList(newsModelRequest.getAuthorId())){throw new AuthorIdDoesNotExistException(newsModelRequest.getAuthorId());}
            NewsModelResponse newsModelResponse= repository.createNews(newsModelRequest);
            newsDtoResponse.map(newsModelResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse updateNews() {
        NewsDtoResponse newsDtoResponse=new NewsDtoResponse();
        try {
            System.out.println(UPDATE_NEWS);
            System.out.println(ENTER_NEWS_ID);
            Long index = takeNumberFromKeyboard();
            NewsModelRequest newsModelRequest = askQuestionsToGetDtoRequest();
            NewsModelRequestWithIndex newsModelRequestWithIndex=new NewsModelRequestWithIndex();
            newsModelRequestWithIndex.setIndex(index);
            newsModelRequestWithIndex.setTitle(newsModelRequest.getTitle());
            newsModelRequestWithIndex.setContent(newsModelRequest.getContent());
            newsModelRequestWithIndex.setAuthorId(newsModelRequest.getAuthorId());
            if (repository.isNewsOnList(index)) {
                if (repository.isAuthorOnList(newsModelRequest.getAuthorId())) {
                    if ((lengthBetween5And30Symbols(newsModelRequest.getTitle())) &&
                            (lengthBetween5And255Symbols(newsModelRequest.getContent()))){
                            NewsModelResponse newsModelResponse=repository.updateNews(newsModelRequestWithIndex);
                            newsDtoResponse.map(newsModelResponse);
                    }
                } else {
                    throw new AuthorIdDoesNotExistException(newsModelRequest.getAuthorId());
                }
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsDtoResponse;
    }

    @Override
    public void deleteNews() {
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
