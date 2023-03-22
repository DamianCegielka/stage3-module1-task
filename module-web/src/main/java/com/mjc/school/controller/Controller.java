package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequestWithIndex;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.io.IOException;
import java.util.List;

public interface Controller {

    void loadData() throws IOException;

    List<NewsDtoResponse> readAllNews() throws IOException;

    NewsDtoResponse readByIdNews(Long indexValue);

    NewsDtoResponse createNews(NewsDtoRequest newsDtoRequest);

    NewsDtoResponse updateNews(NewsDtoRequestWithIndex newsDtoRequestWithIndex);

    Boolean deleteNews(Long index);
}
