package com.mjc.school.service.dto;

import com.mjc.school.repository.dto.NewsModelRequest;

public class NewsDtoRequestWithIndex {

    private Long index;
    private String title;
    private String content;
    private Long authorId;

    public Long getIndex() {
        return index;
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

    public void setIndex(Long index) {
        this.index = index;
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

    public NewsModelRequest mapToNewsModelRequest() {
        NewsModelRequest newsModelRequest = new NewsModelRequest();
        newsModelRequest.setIndex(this.getIndex());
        newsModelRequest.setTitle(this.getTitle());
        newsModelRequest.setContent(this.getContent());
        newsModelRequest.setAuthorId(this.getAuthorId());
        return newsModelRequest;
    }
}
