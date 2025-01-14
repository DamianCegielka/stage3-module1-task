package com.mjc.school.repository.impl;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.ModelRepository;
import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.Author;
import com.mjc.school.repository.entity.News;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelRepositoryImpl implements ModelRepository {

    private final DataSource dataSource = new DataSource();
    private List<News> listNews = dataSource.getListNews();
    private ArrayList<Author> listAuthor = dataSource.getListAuthor();

    @Override
    public void loadDataFromDataSource() {
        dataSource.loadNewsFromDataSource();
        dataSource.loadAuthorsFromDataSource();
    }

    @Override
    public List<News> readAllNews() {
            NewsModelResponse newsModelResponse = new NewsModelResponse();
            listNews.forEach(x -> {
                newsModelResponse.map(x);
                newsModelResponse.print();
            });
        return listNews;
    }

    @Override
    public NewsModelResponse readByIdNews(Long index) {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(index);
            if (b){
                newsModelResponse.map(x);
                newsModelResponse.print();
            }
        });
        return newsModelResponse;
    }

    @Override
    public NewsModelResponse createNews(NewsModelRequest newsModelRequest) {
        News news = new News(newsModelRequest);
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        newsModelResponse.map(news);
        newsModelResponse.print();
        listNews.add(news);
        return newsModelResponse;
    }

    @Override
    public NewsModelResponse updateNews(NewsModelRequest newsModelRequest) {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(newsModelRequest.getIndex());
            if (b) {
                x.setTitle(newsModelRequest.getTitle());
                x.setContent(newsModelRequest.getContent());
                x.setAuthorId(newsModelRequest.getAuthorId());
                x.setLastUpdateTime(LocalDateTime.now());
                newsModelResponse.map(x);
                newsModelResponse.print();
            }
        });
        return newsModelResponse;
    }

    @Override
    public Boolean deleteNews(Long index) {
        if (listNews.removeIf(x -> x.getId().equals(index))) {
            System.out.println(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isAuthorOnList(Long index) {
        for (Author author : listAuthor) {
            if (author.getId().equals(index)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNewsOnList(Long index) {
        for (News news : listNews) {
            if (news.getId().equals(index)) {
                return true;
            }
        }
        return false;
    }

}