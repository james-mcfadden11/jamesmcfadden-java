package com.twitter.domain;

import org.junit.*;

public class UserTests {

    @Test
    public void user_begins_without_followed() {
        User user = new User("Walt");

        Assert.assertNotNull(user.getFollowed());
        Assert.assertEquals(0, user.getFollowed().size());
    }

    @Test
    public void users_with_different_usernames_are_not_equal() {
        User walt = new User("Walt");
        User bob = new User("Bob");

        Assert.assertNotEquals(walt, bob);
    }

    @Test
    public void users_with_different_followed_are_not_equal() {
        User walt = new User("Walt");
        User bob = new User("Bob");
        walt.addFollowed(bob);

        User walt2 = new User("Walt");
        User tom2 = new User("Tom");
        walt2.addFollowed(tom2);

        Assert.assertNotEquals(walt, walt2);
    }

    @Test
    public void users_are_equal() {
        User walt = new User("Walt");
        User tom = new User("Tom");
        walt.addFollowed(tom);

        User walt2 = new User("Walt");
        User tom2 = new User("Tom");
        walt2.addFollowed(tom2);

        Assert.assertEquals(walt, walt2);
    }
}
