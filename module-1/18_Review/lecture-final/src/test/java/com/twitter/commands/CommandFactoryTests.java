package com.twitter.commands;

import org.junit.*;

public class CommandFactoryTests {

    @Test
    public void getCommmandFor_returns_post_command() {
        Command theCommand = CommandFactory.getCommandFor("Walt -> this is my post");

        Assert.assertTrue(theCommand instanceof PostCommand);
    }

    @Test
    public void getCommmandFor_returns_follows_command() {
        Command theCommand = CommandFactory.getCommandFor("Walt follows Tom");

        Assert.assertTrue(theCommand instanceof FollowsCommand);
    }

    @Test
    public void getCommmandFor_returns_wall_command() {
        Command theCommand = CommandFactory.getCommandFor("Walt wall");

        Assert.assertTrue(theCommand instanceof WallCommand);
    }

    @Test
    public void getCommmandFor_returns_read_command() {
        Command theCommand = CommandFactory.getCommandFor("Walt");

        Assert.assertTrue(theCommand instanceof ReadCommand);
    }
}
