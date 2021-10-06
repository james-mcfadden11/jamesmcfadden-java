package com.twitter.commands;

import com.twitter.domain.TwitterService;
import com.twitter.ui.View;
import com.twitter.ui.VoidView;

public class PostCommand extends Command {
    private final TwitterService twitterService;

    public PostCommand(String command, TwitterService twitterService) {
        super(command);
        this.twitterService = twitterService;
    }

    @Override
    public View execute() {
        String[] commandParts = this.getCommand().split(" -> ");
        String userName = commandParts[0];
        String message = commandParts[1];
        twitterService.newPost(userName, message);
        return new VoidView();
    }
}
