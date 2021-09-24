package com.techelevator;

public class HomeworkAssignment {
    private int earnedMarks;
    private int possibleMarks;
    private String submitterName;
    private String letterGrade;

    public HomeworkAssignment(int possibleMarks, String submitterName) {
        this.possibleMarks = possibleMarks;
        this.submitterName = submitterName;
    }

    public int getEarnedMarks() {
        return earnedMarks;
    }

    public int getPossibleMarks() {
        return possibleMarks;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public String getLetterGrade() {
        int gradePercentage = (int)(((double)this.earnedMarks / this.possibleMarks) * 100);
        if (gradePercentage >= 90) {
            this.letterGrade = "A";
        } else if (gradePercentage >= 80) {
            this.letterGrade = "B";
        } else if (gradePercentage >= 70) {
            this.letterGrade = "C";
        } else if (gradePercentage >= 60) {
            this.letterGrade = "D";
        } else {
            this.letterGrade = "F";
        }
        return letterGrade;
    }

    public void setEarnedMarks(int earnedMarks) {
        this.earnedMarks = earnedMarks;
    }

}
