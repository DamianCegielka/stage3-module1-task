package com.mjc.school.repository.dto;

public class NewsModelRequestWithIndex {

    private Long index;
    private String title;
    private String content;
    private Long authorId;

    public Long getIndex() { return index; }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setIndex(Long index) { this.index = index; }

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
