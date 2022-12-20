package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;
import com.mjc.school.repository.impl.RepositoryImpl;
import com.mjc.school.service.Service;
import com.mjc.school.service.Validator;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.AuthorIdDoesNotExistException;
import com.mjc.school.service.exception.NewsDoesNotExistException;

import java.io.IOException;
import java.util.List;

public class ServiceImpl implements Service {

    private final Repository repository = new RepositoryImpl();

    private final Validator validator = new Validator();



    public void loadAllData() {
        repository.loadDataFromDataSource();
    }

    @Override
    public List<News> readAllNews() throws IOException {
        return repository.readAllNews();
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
            validator.lengthBetween5And30Symbols(newsDtoRequest.getTitle());
            validator.lengthBetween5And255Symbols(newsDtoRequest.getContent());
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
                    if ((validator.lengthBetween5And30Symbols(newsDtoRequestWithIndex.getTitle())) &&
                            (validator.lengthBetween5And255Symbols(newsDtoRequestWithIndex.getContent()))) {
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
    public Boolean deleteNews(Long index) {
        Boolean result = false;
        try {
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


}
