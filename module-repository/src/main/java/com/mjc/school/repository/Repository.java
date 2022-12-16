package com.mjc.school.repository;

import com.mjc.school.repository.dto.NewsDtoRequest;
import com.mjc.school.repository.dto.NewsDtoResponse;

import java.io.IOException;

public interface Repository {
    void loadNewsFromFiles();

    void loadAuthorsFromFiles();

    void readAllNews() throws IOException;

    NewsDtoResponse readByIdNews(Long index) throws IOException;

    void createNews(NewsDtoRequest newsDtoRequest);

    void updateNews(Long index, NewsDtoRequest newsDtoRequest);

    void deleteNews(Long index) throws IOException;

    boolean isAuthorOnList(Long index);

    boolean isNewsOnList(Long index);
}
