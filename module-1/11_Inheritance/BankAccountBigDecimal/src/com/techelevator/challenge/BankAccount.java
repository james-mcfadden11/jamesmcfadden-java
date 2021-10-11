package com.techelevator.challenge;

import java.math.BigDecimal;

public class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private BigDecimal balance;

    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal("0");
    }

    public BankAccount(String accountHolderName, String accountNumber, BigDecimal balance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public BigDecimal deposit(BigDecimal amountToDeposit) {
        System.out.println("starting balance: " + this.getBalance());
        System.out.println("amount to deposit: " + amountToDeposit);
        this.balance = this.balance.add(amountToDeposit);
        return this.balance;
    }

    public BigDecimal withdraw(BigDecimal amountToWithdraw) {
        this.balance = this.balance.subtract(amountToWithdraw);
        return this.balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
