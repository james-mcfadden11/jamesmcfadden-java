package com.techelevator;

public class Chef extends Person {
    private int yearsExperience;
    private String specialty;

    public Chef(String firstName, String lastName, int age, int yearsExperience, String specialty) {
        // order of this is important... call to super first
        super(firstName, lastName, age);
//        super();
        this.yearsExperience = yearsExperience;
        this.specialty = specialty;

    }
}
