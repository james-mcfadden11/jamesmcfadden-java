package com.techelevator.challenge;

import java.math.BigDecimal;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public SavingsAccount(String accountHolderName, String accountNumber, BigDecimal balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        BigDecimal newBalance = this.getBalance().subtract(amountToWithdraw);

        if (newBalance.compareTo(new BigDecimal(2)) < 0) {
            System.out.println("Transaction failed - attempted to withdraw more than you have.");
            return this.getBalance();
        } else if (newBalance.compareTo(new BigDecimal(150)) < 0) {
            System.out.println("transaction worked, but with service fee");
            return super.withdraw(amountToWithdraw.add(new BigDecimal(2)));
        } else {
            System.out.println("transaction goes through as normal");
            return super.withdraw(amountToWithdraw);
        }
    }

}
