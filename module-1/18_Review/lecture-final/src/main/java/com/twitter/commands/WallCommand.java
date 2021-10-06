package com.twitter.commands;

import com.twitter.domain.TwitterService;
import com.twitter.domain.Post;
import com.twitter.ui.PostsView;
import com.twitter.ui.View;

import java.util.List;

public class WallCommand extends Command {
    private final TwitterService twitterService;

    public WallCommand(String command, TwitterService twitterService) {
        super(command);
        this.twitterService = twitterService;
    }

    @Override
    public View execute() {
        String[] commandParts = this.getCommand().split(" wall");
        String userName = commandParts[0];
        List<Post> posts = twitterService.wall(userName);
        return new PostsView(posts);
    }
}
