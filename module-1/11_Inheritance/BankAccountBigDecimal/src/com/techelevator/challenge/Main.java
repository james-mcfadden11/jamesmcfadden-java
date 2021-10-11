package com.techelevator.challenge;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
	    BankAccount jimsAccount = new SavingsAccount("jim", "12345", new BigDecimal(100.0));
		System.out.println(jimsAccount.getAccountHolderName());
		System.out.println(jimsAccount.getAccountNumber());
		System.out.println("balance: " + jimsAccount.getBalance());
		System.out.println("withdrawing 45");
		System.out.println("balance: " + jimsAccount.withdraw(new BigDecimal(45)));
		System.out.println("depositing 10");
		System.out.println("balance: " + jimsAccount.deposit(new BigDecimal(10)));
		System.out.println("withdrawing 200");
		System.out.println(jimsAccount.withdraw(new BigDecimal(200)));

    }
}
