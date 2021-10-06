package com.twitter.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final List<User> followed = new ArrayList<>();
    private final String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void addFollowed(User anotherUser) {
        this.followed.add(anotherUser);
    }

    public List<User> getFollowed() {
        return new ArrayList<>(followed);
    }

    @Override
    public String toString() {
        return this.getUserName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(followed, user.followed) && Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followed, userName);
    }
}
