package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizMaker {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the fully qualified name of the file to read in for quiz questions: ");
		String filePath = input.nextLine();

		File file = new File(filePath);

		List<Question> questionsList = new ArrayList<>();

		try (Scanner fileScanner = new Scanner(file)) {
			// iterate through entire text file, convert questions to objects, place in questions arraylist
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] splitLine = line.split("[//|]");
				String[] possibleAnswers = new String[] {splitLine[1], splitLine[2], splitLine[3], splitLine[4]};
				Question question = new Question(splitLine[0], possibleAnswers);
				questionsList.add(question);
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found!");
		}

		// gameplay
		int correctAnswers = 0;
		int totalQuestions = 0;
		for (Question question : questionsList) {

			int num = 1;
			System.out.println(question.getQuestion());
			for (String possibleAnswer : question.getPossibleAnswers()) {
				System.out.println(num + ". " + possibleAnswer.replace("*", ""));
				num++;
			}
			System.out.print("Enter your guess: ");
			String guess = input.next();
			int ansAsInt = Integer.parseInt(guess);
			if (ansAsInt == question.getCorrectAnswer()) {
				System.out.println("RIGHT!");
				correctAnswers++;
			} else {
				System.out.println("WRONG :-(");
			}
			System.out.println("------------------");
			totalQuestions++;
		}
		System.out.println("You got " + correctAnswers + " answer(s) correct out of " + totalQuestions + " questions.");
	}
}
