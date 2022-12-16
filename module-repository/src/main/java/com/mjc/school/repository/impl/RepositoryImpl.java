package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.dto.NewsDtoRequest;
import com.mjc.school.repository.dto.NewsModelResponse;
import com.mjc.school.repository.entity.Author;
import com.mjc.school.repository.entity.News;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RepositoryImpl implements Repository {


    private ArrayList<News> listNews = new ArrayList<>();
    private ArrayList<Author> listAuthor = new ArrayList<>();

    @Override
    public void loadNewsFromFiles() {
        String filePath = "module-repository\\src\\main\\java\\resources\\news.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayLine = line.split(";");
                News news = new News();
                news.setId(Long.parseLong(arrayLine[0]));
                news.setTitle(arrayLine[1]);
                news.setContent(arrayLine[2]);
                news.setCreateDate(LocalDateTime.parse(arrayLine[3]));
                news.setLastUpdateTime(LocalDateTime.parse(arrayLine[4]));
                news.setAuthorId(Long.parseLong(arrayLine[5]));
                listNews.add(news);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAuthorsFromFiles() {
        String filePath = "module-repository\\src\\main\\java\\resources\\author.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayLine = line.split(";");
                Author author = new Author();
                author.setId(Long.parseLong(arrayLine[0]));
                author.setName(arrayLine[1]);
                listAuthor.add(author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readAllNews() throws IOException {
        try {
            NewsModelResponse newsModelResponse = new NewsModelResponse();
            listNews.forEach(x -> {
                newsModelResponse.map(x);
                newsModelResponse.print();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NewsModelResponse readByIdNews(Long index) throws IOException {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(index);
            if (b) newsModelResponse.map(x);
            if (b) newsModelResponse.print();
        });
        return newsModelResponse;
    }

    @Override
    public NewsModelResponse createNews(NewsDtoRequest newsDtoRequest) {
        News news = new News(newsDtoRequest);
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        newsModelResponse.map(news);
        newsModelResponse.print();
        listNews.add(news);
        return newsModelResponse;
    }

    @Override
    public void updateNews(Long index, NewsDtoRequest newsDtoRequest) {
        NewsModelResponse newsModelResponse = new NewsModelResponse();
        listNews.forEach(x -> {
            boolean b = x.getId().equals(index);
            if (b) x.setTitle(newsDtoRequest.getTitle());
            if (b) x.setContent(newsDtoRequest.getContent());
            if (b) x.setAuthorId(newsDtoRequest.getAuthorId());
            if (b) x.setLastUpdateTime(LocalDateTime.now());
            if (b) newsModelResponse.map(x);
            if (b) newsModelResponse.print();
        });
    }

    @Override
    public void deleteNews(Long index) {
        if (listNews.removeIf(x -> x.getId().equals(index))) {
            System.out.println(true);
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