package com.techelevator;

import java.io.*;

public class Files {

    public static void main(String[] args) {

        // create a blank file
        try {
            File file = new File("blah.txt");
            file.createNewFile();
        } catch(IOException e) {
            System.out.println("Could not create file!");
        }

        // create or overwrite file and write to it
        File file2 = new File("blah2.txt");

        try(PrintWriter writer = new PrintWriter(file2)) {
            writer.print("Hello!");

        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        }

        // append
        try(FileWriter fWriter = new FileWriter(file2, true);
            PrintWriter writer = new PrintWriter(fWriter)) {

            writer.print("Hello!");

        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
        } catch(IOException e) {
            System.out.println("IOException!");
        }
    }
}
