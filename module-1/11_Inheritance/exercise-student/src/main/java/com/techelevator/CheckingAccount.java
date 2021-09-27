package com.techelevator;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        int newBalance = this.getBalance() - amountToWithdraw;
        if (newBalance < 0 && newBalance > -100) {
            // withdraw amount, plus overdraft fee
            return super.withdraw(amountToWithdraw + 10);
        } else if (newBalance < -100) {
            System.out.println("Transaction failed - attempted to withdraw beyond allowable overdraft.");
            return this.getBalance();
        } else {
            // normal withdrawal
            return super.withdraw(amountToWithdraw);
        }
    }
}
