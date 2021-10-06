package com.twitter;

import com.twitter.commands.CommandFactory;
import com.twitter.ui.View;

public class Twitter {
    private final View view;

    public Twitter(View view) {
        this.view = view;
    }

    public void start() {
        final String QUIT = "quit";

        String command = view.getCommand();

        while(!command.equals(QUIT)) {
            CommandFactory.getCommandFor(command).execute().display();

            command = view.getCommand();
        }

    }
}
