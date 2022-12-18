package com.mjc.school.repository;

import com.mjc.school.repository.dto.NewsDtoRequest;
import com.mjc.school.repository.dto.NewsModelRequestWithIndex;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.News;

import java.io.IOException;
import java.util.List;

public interface Repository {
    void loadNewsFromDataSource();

    void loadAuthorsFromDataSource();

    List<News> readAllNews() throws IOException;

    NewsModelResponse readByIdNews(Long index) throws IOException;

    NewsModelResponse createNews(NewsDtoRequest newsDtoRequest);

    NewsModelResponse updateNews(NewsModelRequestWithIndex newsModelRequestWithIndex);

    Boolean deleteNews(Long index) throws IOException;

    boolean isAuthorOnList(Long index);

    boolean isNewsOnList(Long index);
}
