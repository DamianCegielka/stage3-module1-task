package com.mjc.school.service.impl;

import com.mjc.school.repository.RepositoryModel;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.impl.RepositoryModelImpl;
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

    private final RepositoryModel serviceRepository = new RepositoryModelImpl();
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
                throw new AuthorIdDoesNotExistException(String.format(ErrorCodes.AUTHOR_ID_DOES_NOT_EXIST.getMessage(),newsDtoRequest.getAuthorId()));
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
                    throw new AuthorIdDoesNotExistException(String.format(ErrorCodes.AUTHOR_ID_DOES_NOT_EXIST.getMessage(), newsDtoRequestWithIndex.getAuthorId()));
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
