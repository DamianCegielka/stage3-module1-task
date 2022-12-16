package com.mjc.school.repository.entity;

public class Author {
    private Long id;
    private String name;

    public Author() {
        //default constructor
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }
}
