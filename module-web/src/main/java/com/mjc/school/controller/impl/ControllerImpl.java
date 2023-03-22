package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.ServiceDto;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.ServiceDtoImpl;

import java.io.IOException;
import java.util.List;

public class ControllerImpl implements Controller {

    private final ServiceDto controllerServiceDto = new ServiceDtoImpl();
    private int chosenNumber = -1;


    @Override
    public void loadData() throws IOException {
        controllerServiceDto.loadAllData();
    }

    @Override
    public List<NewsDtoResponse> readAllNews() throws IOException {
        return controllerServiceDto.readAllNews();
    }

    @Override
    public NewsDtoResponse readByIdNews(Long index) {
        NewsDtoResponse newsDtoResponse;
        newsDtoResponse = controllerServiceDto.readByIdNews(index);
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest) {
        NewsDtoResponse newsDtoResponse;
        newsDtoResponse = controllerServiceDto.createNews(newsDtoRequest);
        return newsDtoResponse;
    }

    @Override
    public NewsDtoResponse updateNews(NewsDtoRequestWithIndex newsDtoRequestWithIndex) {
        NewsDtoResponse newsDtoResponse;
        newsDtoResponse = controllerServiceDto.updateNews(newsDtoRequestWithIndex);
        return newsDtoResponse;
    }

    @Override
    public Boolean deleteNews(Long index) {
        Boolean result = false;
        result = controllerServiceDto.deleteNews(index);
        return result;
    }

}
