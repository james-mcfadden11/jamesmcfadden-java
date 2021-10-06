package com.twitter.commands;

import com.twitter.domain.TwitterService;
import com.twitter.domain.Post;
import com.twitter.ui.PostsView;
import com.twitter.ui.View;

import java.util.ArrayList;
import java.util.List;

public class ReadCommand extends Command {
    private final TwitterService twitterService;

    public ReadCommand(String command, TwitterService twitterService) {
        super(command);
        this.twitterService = twitterService;
    }

    @Override
    public View execute() {
        if(getCommand().isEmpty()) return new PostsView(new ArrayList<>());

        String user = getCommand().split(" ")[0];
        List<Post> posts = this.twitterService.readFor(user);
        return new PostsView(posts);
    }
}
