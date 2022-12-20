package com.mjc.school.repository.impl;

import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.RepositoryModel;
import com.mjc.school.repository.dto.NewsModelRequest;
import com.mjc.school.repository.dto.NewsModelRequestWithIndex;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.Author;
import com.mjc.school.repository.entity.News;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositoryModelImpl implements RepositoryModel {

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
        try {
            NewsModelResponse newsModelResponse = new NewsModelResponse();
            listNews.forEach(x -> {
                newsModelResponse.map(x);
                newsModelResponse.print();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNews;
    }

    @Override
    public NewsModelResponse readByIdNews(Long index) {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(index);
            if (b) newsModelResponse.map(x);
            if (b) newsModelResponse.print();
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
    public NewsModelResponse updateNews(NewsModelRequestWithIndex newsModelRequestWithIndex) {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(newsModelRequestWithIndex.getIndex());
            if (b) x.setTitle(newsModelRequestWithIndex.getTitle());
            if (b) x.setContent(newsModelRequestWithIndex.getContent());
            if (b) x.setAuthorId(newsModelRequestWithIndex.getAuthorId());
            if (b) x.setLastUpdateTime(LocalDateTime.now());
            if (b) newsModelResponse.map(x);
            if (b) newsModelResponse.print();

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