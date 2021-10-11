package com.techelevator.tictactoe_encapsulated;

public class Person {
    private String firstName;
    private String lastName;
    private int howManyPersons = 0;

    // static belongs to the class only
    // not static means it belongs to the instance of the class

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        howManyPersons++;   // or Person.howManyPersons++;
    }

    public void print() {
        System.out.println(firstName + " " + lastName + " how many? " + howManyPersons);
    }


}
