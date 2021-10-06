package com.twitter.ui;

import com.twitter.domain.Post;

import java.time.format.DateTimeFormatter;

public class PostFormatter {
    public String format(Post p) {
        // TODO: update the format of when so that it shows how long since it was posted rather than the timestamp

        return p.getUser() + " - " + p.getMessage() + " " + p.getWhen().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
