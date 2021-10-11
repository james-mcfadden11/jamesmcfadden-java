package com.techelevator;
import org.junit.*;

public class SavingsAccountTest {
    /*
    normal case
    balance = 1000
    withdraw = 100
    result balance = 900

    service fee incurred
    declined - cannot withdraw
    withdraw a negative number
    */

    @Test
    public void withdraw_returns_900_after_withdrawing_100_from_1000() {
        // arrange
        SavingsAccount mySavingsAccount = new SavingsAccount("", "", 1000);
        // act
        int remainingBalance = mySavingsAccount.withdraw(100);
        // assert
        Assert.assertEquals(900, remainingBalance);
    }

    @Test
    public void withdraw_returns_same_balance_afterwithdrawingnegative() {
        // arrange
        SavingsAccount mySavingsAccount = new SavingsAccount("", "", 200);
        // act
        int remainingBalance = mySavingsAccount.withdraw(-100);
        // assert
        Assert.assertEquals(200, remainingBalance);
    }


}
