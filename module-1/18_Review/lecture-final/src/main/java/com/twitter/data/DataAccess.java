package com.twitter.data;

import com.twitter.domain.Post;
import com.twitter.domain.User;

import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    private static final List<User> users = new ArrayList<>();
    private static final List<Post> posts = new ArrayList<>();

    public void clear() {
        users.clear();
        posts.clear();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public User getUserByUsername(String userName) {
        for(User u : users) {
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }
}
