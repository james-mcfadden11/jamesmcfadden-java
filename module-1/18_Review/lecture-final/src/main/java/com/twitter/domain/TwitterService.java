package com.twitter.domain;

import com.twitter.data.DataAccess;

import java.util.*;

public class TwitterService {
    private final DataAccess dataAccess = new DataAccess(); // TODO: accept as interface

    public void newPost(String userName, String message) {
        User user = dataAccess.getUserByUsername(userName);

        if(user == null) {
            user = new User(userName);
            dataAccess.addUser(user);
        }

        final Post post = new Post(user, message);
        this.dataAccess.addPost(post);
    }

    public void follow(String userName, String userToFollow) {
        final User user = dataAccess.getUserByUsername(userName);

        if(user == null) {
            return;
        }

        final User newUserToFollow = dataAccess.getUserByUsername(userToFollow);

        if(newUserToFollow == null) {
            return;
        }
        user.addFollowed(newUserToFollow);
    }

    public List<Post> wall(String userName) {
        final User user = dataAccess.getUserByUsername(userName);

        if(user == null) {
            return new ArrayList<>();
        }

        final List<User> allOptions = user.getFollowed();
        allOptions.add(user);

        final List<Post> results = new ArrayList<>();

        for(Post post : dataAccess.getPosts()) {
            for(User theUser : allOptions) {
                if(post.getUser().equals(theUser)) {
                    results.add(post);
                }
            }
        }

        // TODO: order results by timestamp

        return results;
    }

    public List<Post> readFor(String userName) {
        User user =  dataAccess.getUserByUsername(userName);

        if(user == null) {
            return new ArrayList<>();
        }

        List<Post> results = new ArrayList<>();

        for(Post post : dataAccess.getPosts()) {
            if(post.getUser().equals(user)) {
                results.add(post);
            }
        }

        return results;
    }
}
