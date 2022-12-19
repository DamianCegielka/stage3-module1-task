package com.mjc.school.repository.entity;

import com.mjc.school.repository.dto.NewsModelRequest;

import java.time.LocalDateTime;

public class News {

    private static Long idGenerator = 0l;
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateTime;
    private Long authorId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public News(NewsModelRequest newsDTOCreation) {
        idGenerator++;
        this.id = News.idGenerator;
        this.title = newsDTOCreation.getTitle();
        this.createDate = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
        this.content = newsDTOCreation.getContent();
        this.authorId = newsDTOCreation.getAuthorId();
    }

    public News() {
        idGenerator++;
        this.id = News.idGenerator;
    }

}
