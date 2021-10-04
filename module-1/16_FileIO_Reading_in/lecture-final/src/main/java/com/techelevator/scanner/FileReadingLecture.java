package com.techelevator.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadingLecture {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("What is the path to the file? ");
        String filePath = input.nextLine();

        File file = new File(filePath);

        if(!file.exists()) {
            System.out.println("The file does not exist!");
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);

                String name;
                int[] quizScores;

                // line -> "walt,50,65,35,100"

                String[] lineParts = line.split(","); // [ "walt", "50", "65", "35", "100" ]

                name = lineParts[0];
                quizScores = new int[lineParts.length - 1];
                for (int i = 1; i < lineParts.length; i++) {
                    try {
                        int score = Integer.parseInt(lineParts[i]);
                        quizScores[i - 1] = score;
                    } catch(NumberFormatException e) {
                        quizScores[i - 1] = 0;
                    }
                }

                System.out.println(name);
                for(int score : quizScores) {
                    System.out.println(score);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found!");
        }

    }
}
