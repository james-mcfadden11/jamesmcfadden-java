package com.techelevator;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        int newBalance = this.getBalance() - amountToWithdraw;

        if (newBalance - 2 < 0) {
            System.out.println("Transaction failed - attempted to withdraw more than you have.");
            return this.getBalance();
        } else if (newBalance < 150) {
            System.out.println("transaction worked, but with service fee");
            return super.withdraw(amountToWithdraw + 2);
        } else {
            System.out.println("transaction goes through as normal");
            return super.withdraw(amountToWithdraw);
        }
    }

}
