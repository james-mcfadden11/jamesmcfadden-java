package com.twitter.domain;

import com.twitter.data.DataAccess;

import org.junit.*;

import java.util.List;

public class TwitterServiceTests {
    private DataAccess dataAccess;
    private final String USER_NAME = "Walt";
    private final String USER_NAME2 = "Tom";
    private final String INVALID_USER_NAME = "INVALID";

    @Before
    public void setup() {
        final User fakeUser = new User(USER_NAME);
        final User fakeUser2 = new User(USER_NAME2);

        dataAccess = new DataAccess();
        dataAccess.addUser(fakeUser);
        dataAccess.addUser(fakeUser2);
    }

    @After
    public void cleanup() {
        dataAccess.clear();
    }

    @Test
    public void newPost_adds_a_post() {
        final int initialSize = dataAccess.getPosts().size();
        final String postMessage = "My message";
        final TwitterService service = new TwitterService();

        service.newPost(USER_NAME, postMessage);

        List<Post> posts = dataAccess.getPosts();
        Assert.assertEquals(initialSize + 1, posts.size());
        Assert.assertEquals(postMessage, posts.get(posts.size() - 1).getMessage());
    }

    @Test
    public void newPost_for_invalid_user_creates_user_and_post() {
        final int initialSize = dataAccess.getPosts().size();
        final String postMessage = "My message";
        final TwitterService service = new TwitterService();

        service.newPost(INVALID_USER_NAME, postMessage);

        List<Post> posts = dataAccess.getPosts();
        Assert.assertEquals(initialSize + 1, posts.size());
        Assert.assertNotNull(dataAccess.getUserByUsername(INVALID_USER_NAME));
    }

    @Test
    public void follow_adds_a_follower() {
        final TwitterService service = new TwitterService();

        service.follow(USER_NAME, USER_NAME2);

        User follower = dataAccess.getUserByUsername(USER_NAME);
        User followee = dataAccess.getUserByUsername(USER_NAME2);

        Assert.assertEquals(1, follower.getFollowed().size());
        Assert.assertEquals(follower.getFollowed().get(0), followee);
    }

    @Test
    public void follow_a_user_that_does_not_exist_does_nothing() {
        final TwitterService service = new TwitterService();

        service.follow(USER_NAME, INVALID_USER_NAME);

        User follower = dataAccess.getUserByUsername(USER_NAME);

        Assert.assertEquals(0, follower.getFollowed().size());
    }

    @Test
    public void follow_when_the_follower_does_not_exist_does_nothing() {
        final TwitterService service = new TwitterService();

        service.follow(INVALID_USER_NAME, USER_NAME);

        User follower = dataAccess.getUserByUsername(INVALID_USER_NAME);

        Assert.assertNull(follower);
    }

    @Test
    public void readFor_shows_my_posts() {
        final TwitterService service = new TwitterService();
        User user = dataAccess.getUserByUsername(USER_NAME);
        dataAccess.addPost(new Post(user, "My first message"));
        dataAccess.addPost(new Post(user, "My second message"));

        List<Post> myPosts = service.readFor(USER_NAME);

        Assert.assertNotNull(myPosts);
        Assert.assertEquals(2, myPosts.size());
    }

    @Test
    public void readFor_invalid_user_gets_zero_posts() {
        final TwitterService service = new TwitterService();

        List<Post> myPosts = service.readFor(INVALID_USER_NAME);

        Assert.assertNotNull(myPosts);
        Assert.assertEquals(0, myPosts.size());
    }

    @Test
    public void wall_returns_my_posts_and_follower_posts() {
        Assert.fail("TBD");
    }
}
