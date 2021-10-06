package com.twitter.commands;

import com.twitter.domain.TwitterService;
import com.twitter.ui.View;
import com.twitter.ui.VoidView;

public class FollowsCommand extends Command {
    private final TwitterService twitterService;

    public FollowsCommand(String command, TwitterService twitterService) {
        super(command);
        this.twitterService = twitterService;
    }

    @Override
    public View execute() {
        String[] commandParts = getCommand().split(" follows ");
        String userName = commandParts[0];
        String userToFollow = commandParts[1];
        twitterService.follow(userName, userToFollow);
        return new VoidView();
    }
}
