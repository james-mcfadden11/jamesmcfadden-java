package com.techelevator.challenge;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public CheckingAccount(String accountHolderName, String accountNumber, BigDecimal balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        BigDecimal newBalance = this.getBalance().subtract(amountToWithdraw);
        if (newBalance.compareTo(new BigDecimal(0)) < 0 && newBalance.compareTo(new BigDecimal(-100)) > 0) {
            // withdraw amount, plus overdraft fee
            System.out.println("overdraft");
            super.withdraw(amountToWithdraw.add(new BigDecimal(10)));
            return this.getBalance();
        } else if (newBalance.compareTo(new BigDecimal(-100)) < 0) {
            System.out.println("Transaction failed - attempted to withdraw beyond allowable overdraft.");
            return this.getBalance();
        } else {
            // normal withdrawal
            System.out.println("normal withdrawal");
            super.withdraw(amountToWithdraw);
            return this.getBalance();
        }
    }

}
