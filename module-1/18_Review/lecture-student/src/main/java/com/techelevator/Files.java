package com.techelevator;

import java.io.*;

public class Files {
    public static void main(String[] args) {

        // create a blank file
        try {
            File file = new File("blah.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("could not create file");
        }

        // create or overwrite file
        try (PrintWriter writer = new PrintWriter(new File("blah2.txt"))) {
            writer.println("hello");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

        // append file
        // this is ugly -->
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File("blah.txt"), true))) {
            writer.println("hello");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }
}
