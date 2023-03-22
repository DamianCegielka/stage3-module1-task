package com.mjc.school.service.impl;

import com.mjc.school.repository.ModelRepository;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.impl.ModelRepositoryImpl;
import com.mjc.school.service.ServiceDto;
import com.mjc.school.service.Validator;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.AuthorIdDoesNotExistException;
import com.mjc.school.service.exception.ErrorCodes;
import com.mjc.school.service.exception.NewsDoesNotExistException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDtoImpl implements ServiceDto {

    private final ModelRepository serviceRepository = new ModelRepositoryImpl();
    private final Validator serviceValidator = new Validator();

    public void loadAllData() {
        serviceRepository.loadDataFromDataSource();
    }

    @Override
    public List<NewsDtoResponse> readAllNews() throws IOException {
        List<NewsDtoResponse> listResult = new ArrayList<>();
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        serviceRepository.readAllNews().forEach(x ->
                listResult.add(newsDtoResponse.map(x)));
        return listResult;
    }

    @Override
    public NewsDtoResponse readByIdNews(Long index) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            if (serviceRepository.isNewsOnList(index)) {
                NewsModelResponse newsModelResponse = serviceRepository.readByIdNews(index);
                newsDtoResponse.map(newsModelResponse);
            } else {
                throw new NewsDoesNotExistException(String.format(ErrorCodes.NEWS_ID_DOES_NOT_EXIST.getMessage(),
                                                    index));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            serviceValidator.validateLengthNewsTitle(newsDtoRequest.getTitle());
            serviceValidator.validateLengthNewsContent(newsDtoRequest.getContent());
            if (!serviceRepository.isAuthorOnList(newsDtoRequest.getAuthorId())) {
                throw new AuthorIdDoesNotExistException(String.format(ErrorCodes.AUTHOR_ID_DOES_NOT_EXIST.getMessage(),
                                                                        newsDtoRequest.getAuthorId()));
            }
            NewsModelResponse newsModelResponse = serviceRepository.createNews(newsDtoRequest.mapToNewsModelRequest());
            newsDtoResponse.map(newsModelResponse);
        } catch (AuthorIdDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse updateNews(NewsDtoRequestWithIndex newsDtoRequestWithIndex) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        try {
            if (serviceRepository.isNewsOnList(newsDtoRequestWithIndex.getIndex())) {
                if (serviceRepository.isAuthorOnList(newsDtoRequestWithIndex.getAuthorId())) {
                    if ((serviceValidator.validateLengthNewsTitle(newsDtoRequestWithIndex.getTitle())) &&
                            (serviceValidator.validateLengthNewsContent(newsDtoRequestWithIndex.getContent()))) {
                        NewsModelResponse newsModelResponse = serviceRepository.updateNews(newsDtoRequestWithIndex.mapToNewsModelRequest());
                        newsDtoResponse.map(newsModelResponse);
                    }
                } else {
                    throw new AuthorIdDoesNotExistException(String.format(ErrorCodes.AUTHOR_ID_DOES_NOT_EXIST.getMessage(),
                            newsDtoRequestWithIndex.getAuthorId()));
                }
            } else {
                throw new NewsDoesNotExistException(String.format(ErrorCodes.NEWS_ID_DOES_NOT_EXIST.getMessage(),
                        newsDtoRequestWithIndex.getIndex()));
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
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
                throw new NewsDoesNotExistException(String.format(ErrorCodes.NEWS_ID_DOES_NOT_EXIST.getMessage(),
                        index));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }


}
