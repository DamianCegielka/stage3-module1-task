package com.mjc.school.repository;

import com.mjc.school.repository.entity.Author;
import com.mjc.school.repository.entity.News;

import java.util.ArrayList;
import java.util.List;

public interface DataSource {


    List<News> getListNews();

    ArrayList<Author> getListAuthor();

    void loadNewsFromDataSource();

    void loadAuthorsFromDataSource();
}
