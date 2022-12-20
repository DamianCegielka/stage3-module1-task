package com.mjc.school.service.impl;

import com.mjc.school.repository.RepositoryModel;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;
import com.mjc.school.repository.impl.RepositoryModelImpl;
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

    private final RepositoryModel serviceRepository = new RepositoryModelImpl();
    private final Validator serviceValidator = new Validator();


    public void loadAllData() {
        serviceRepository.loadDataFromDataSource();
    }

    @Override
    public List<News> readAllNews() throws IOException {
        return serviceRepository.readAllNews();
    }

    @Override
    public NewsDtoResponse readByIdNews(Long index) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            if (serviceRepository.isNewsOnList(index)) {
                NewsModelResponse newsModelResponse = serviceRepository.readByIdNews(index);
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
            serviceValidator.lengthBetween5And30Symbols(newsDtoRequest.getTitle());
            serviceValidator.lengthBetween5And255Symbols(newsDtoRequest.getContent());
            if (!serviceRepository.isAuthorOnList(newsDtoRequest.getAuthorId())) {
                throw new AuthorIdDoesNotExistException(newsDtoRequest.getAuthorId());
            }
            NewsModelResponse newsModelResponse = serviceRepository.createNews(newsDtoRequest.mapToNewsModelRequest());
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
            if (serviceRepository.isNewsOnList(newsDtoRequestWithIndex.getIndex())) {
                if (serviceRepository.isAuthorOnList(newsDtoRequestWithIndex.getAuthorId())) {
                    if ((serviceValidator.lengthBetween5And30Symbols(newsDtoRequestWithIndex.getTitle())) &&
                            (serviceValidator.lengthBetween5And255Symbols(newsDtoRequestWithIndex.getContent()))) {
                        NewsModelResponse newsModelResponse = serviceRepository.updateNews(newsDtoRequestWithIndex.mapToNewsModelRequestWithIndex());
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
            if (serviceRepository.isNewsOnList(index)) {
                result = serviceRepository.deleteNews(index);
            } else {
                throw new NewsDoesNotExistException(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
