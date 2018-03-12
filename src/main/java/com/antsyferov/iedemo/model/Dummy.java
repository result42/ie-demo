package com.antsyferov.iedemo.model;

public class Dummy {
    private long userId;
    private long id;
    private String title;
    private String body;

    public Dummy() {
    }

    public Dummy(long userId, long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
