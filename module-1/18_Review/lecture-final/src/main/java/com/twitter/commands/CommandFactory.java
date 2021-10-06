package com.twitter.commands;

import com.twitter.domain.TwitterService;

public class CommandFactory {

    public static Command getCommandFor(String command) {
        final TwitterService twitterService = new TwitterService();

        if(isPost(command)) return new PostCommand(command, twitterService);

        if(isFollow(command)) return new FollowsCommand(command, twitterService);

        if(isWall(command)) return new WallCommand(command, twitterService);

        return new ReadCommand(command, twitterService);
    }

    // TODO: improve command parsing to use Regular Expressions
    private static boolean isPost(String command) {
        return command.contains("->");
    }

    private static boolean isFollow(String command) {
        return command.contains("follows");
    }

    private static boolean isWall(String command) {
        return command.contains("wall");
    }

}
