package com.twitter.commands;

import com.twitter.ui.View;

public abstract class Command {
    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public abstract View execute();
}
