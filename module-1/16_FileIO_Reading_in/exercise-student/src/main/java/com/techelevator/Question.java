package com.techelevator;

public class Question {
    private String question;
    private String[] possibleAnswers;
    private int correctAnswer;

    public Question(String question, String[] possibleAnswers) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
    }

    public int getCorrectAnswer() {
        for (int i = 0; i < possibleAnswers.length; i++) {
            if (possibleAnswers[i].endsWith("*")) {
                this.correctAnswer = i + 1;
                return this.correctAnswer;
            }
        }
        // should not reach this line
        return -1;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }
}
