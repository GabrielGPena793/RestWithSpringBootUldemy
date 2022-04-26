package com.uldemy;

public class Greetings {

    private final Long id;
    private final String cotent;

    public Greetings(Long id, String cotent) {
        this.id = id;
        this.cotent = cotent;
    }

    public Long getId() {
        return id;
    }

    public String getCotent() {
        return cotent;
    }
}
