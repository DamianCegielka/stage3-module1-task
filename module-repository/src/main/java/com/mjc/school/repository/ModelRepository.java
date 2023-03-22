package com.mjc.school.repository;

import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;

import java.io.IOException;
import java.util.List;

public interface ModelRepository {

    void loadDataFromDataSource();

    List<News> readAllNews() throws IOException;

    NewsModelResponse readByIdNews(Long index);

    NewsModelResponse createNews(NewsModelRequest newsModelRequest);

    NewsModelResponse updateNews(NewsModelRequest newsModelRequest);

    Boolean deleteNews(Long index) throws IOException;

    boolean isAuthorOnList(Long index);

    boolean isNewsOnList(Long index);
}
