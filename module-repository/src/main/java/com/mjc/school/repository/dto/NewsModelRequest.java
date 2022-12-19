package com.mjc.school.repository.dto;

public class NewsModelRequest {
    String title;
    String content;
    Long authorId;

    public NewsModelRequest() {
        //This is empty Constructor
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
