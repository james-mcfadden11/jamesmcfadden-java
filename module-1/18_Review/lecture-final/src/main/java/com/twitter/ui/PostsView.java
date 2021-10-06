package com.twitter.ui;

import com.twitter.domain.Post;

import java.util.List;

public class PostsView extends View {
    private final List<Post> posts;
    private final PostFormatter postFormatter = new PostFormatter(); // TODO: accept as interface

    public PostsView(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void display() {
        for(Post p : posts) {
            String formattedPost = postFormatter.format(p);
            System.out.println(formattedPost);
        }
    }
}
