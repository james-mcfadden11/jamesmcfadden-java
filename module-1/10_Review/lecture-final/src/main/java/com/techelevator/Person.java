package com.techelevator;

public class Person {
    private String firstName;
    private String lastName;
    public static int howManyPersons = 0;

    public Person(String firstName, String lastName) {
        howManyPersons++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void print() {
        System.out.println(firstName + " " + lastName + " How Many: " + howManyPersons);
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
