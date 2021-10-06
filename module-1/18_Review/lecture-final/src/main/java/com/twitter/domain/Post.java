package com.twitter.domain;

import java.time.LocalDateTime;

public class Post {
    private final User user;
    private final String message;
    private final LocalDateTime when;

    public Post(User user, String message) {
        this.user = user;
        this.message = message;
        this.when = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }
    public String getMessage() {
        return message;
    }
    public LocalDateTime getWhen() {
        return when;
    }
}

