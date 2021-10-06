package com.twitter.ui;

import java.util.Scanner;

public abstract class View {
    private final Scanner keyboardInput = new Scanner(System.in);

    public String getCommand() {
        System.out.print("> ");
        return keyboardInput.nextLine();
    }

    public abstract void display();
}
