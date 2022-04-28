package com.uldemy.model;

public class Greetings {

    private final Long id;
    private final String content;

    public Greetings(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
