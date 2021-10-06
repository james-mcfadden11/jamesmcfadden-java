package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class FindAndReplace {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("What is the search word? ");
        String searchWord = userInput.nextLine();

        System.out.println("What is the replacement word? ");
        String replacementWord = userInput.nextLine();

        System.out.println("What is the source file? ");
        String file = userInput.nextLine();
        File originalFile = new File(file);

        System.out.println("What is the destination file? ");
        file = userInput.nextLine();
        File newFile = new File(file);

        try (Scanner fileReader = new Scanner(originalFile);
             PrintWriter fileWriter = new PrintWriter(newFile)) {
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                line = line.replaceAll(searchWord, replacementWord);
                fileWriter.println(line);
            }
        } catch (FileNotFoundException e1) {
            System.out.println("One or both of the files not found!");
        }
    }
}
