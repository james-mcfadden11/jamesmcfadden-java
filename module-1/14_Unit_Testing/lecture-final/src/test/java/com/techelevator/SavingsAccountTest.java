package com.techelevator;

import org.junit.*;

public class SavingsAccountTest {

    /*
     normal case
     pre-reqs balance => 1000
     withdraw => 100
     result => 900


     service fee incurred

     we aren't allowed to withdraw

     withdraw negative money

     */

    @Test
    public void withdraw_returns_900_after_withdrawing_100_from_1000() {
        // Arrange
        SavingsAccount mySavingsAccount = new SavingsAccount("", "", 1000);

        // Act
        int remainingBalance = mySavingsAccount.withdraw(100);

        // Assert
        Assert.assertEquals(900, remainingBalance);
    }

    @Test
    public void withdraw_returns_same_balance_after_withdrawing_negative() {
        // Arrange
        SavingsAccount mySavingsAccount = new SavingsAccount("", "", 200);

        // Act
        int remainingBalance = mySavingsAccount.withdraw(-100);

        // Assert
        Assert.assertEquals(200, remainingBalance);
    }
}
